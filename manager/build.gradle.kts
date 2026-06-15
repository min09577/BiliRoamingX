plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    id("org.jetbrains.kotlin.plugin.noarg") version "1.9.0" apply false
}

buildscript {
    repositories { mavenCentral() }
    dependencies {
        classpath("org.ow2.asm:asm:9.6")
        classpath("org.ow2.asm:asm-commons:9.6")
    }
}

android {
    namespace = "app.revanced.biliroaming.manager"
    compileSdk = 34

    defaultConfig {
        applicationId = "app.revanced.biliroaming.manager"
        minSdk = 26
        targetSdk = 33  // 14+ 拒绝 v1-only 签名(targetSdk≥34), 暂降 33 兼容 jarsigner
        versionName = project.version.toString()
        versionCode = 1
    }

    signingConfigs {
        create("manager") {
            storeFile = rootProject.file("debug.keystore")
            storePassword = "android"
            keyAlias = "androiddebugkey"
            keyPassword = "android"
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            signingConfig = signingConfigs.getByName("manager")
        }
        debug {
            signingConfig = signingConfigs.getByName("manager")
        }
    }

    buildFeatures {
        viewBinding = true
    }

    packaging {
        resources {
            excludes += setOf(
                "META-INF/versions/**"
            )
            // 处理库的重复 META-INF 文件 (Android Gradle Plugin 会自动处理签名)
            pickFirsts += setOf(
                "META-INF/LICENSE",
                "META-INF/NOTICE",
                "META-INF/DEPENDENCIES"
            )
        }
    }
}

dependencies {
    implementation(libs.revanced.patcher) {
        exclude(group = "xmlpull", module = "xmlpull")
        exclude(group = "xpp3", module = "xpp3")
    }
    implementation(libs.smali) {
        exclude(group = "xmlpull", module = "xmlpull")
        exclude(group = "xpp3", module = "xpp3")
    }

    // BouncyCastle PKIX for APK signing
    implementation("org.bouncycastle:bcpkix-jdk18on:1.78")

    implementation("androidx.core:core-ktx:1.13.1")
    implementation("androidx.appcompat:appcompat:1.7.0")
    implementation("com.google.android.material:material:1.12.0")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.8.4")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.8.4")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.9.0")
}

// Copy patches.jar and integrations.apk from other modules' build outputs to assets
tasks.register<Copy>("copyPatcherAssets") {
    dependsOn(":patches:dist")
    dependsOn(":integrations:app:assembleRelease")

    val patchesModule = project(":patches")
    val integrationsModule = project(":integrations:app")

    from(patchesModule.layout.buildDirectory.dir("libs")) {
        include("*.jar")
        rename { "patches.jar" }
    }
    from(integrationsModule.layout.buildDirectory.dir("outputs/apk/release")) {
        include("*.apk")
        rename { "integrations.apk" }
    }
    into(layout.projectDirectory.dir("src/main/assets"))
}

tasks.named("preBuild") {
    dependsOn("copyPatcherAssets")
}

// 修补 revanced-patcher JAR，修复 Android 上的 ResourceContext.decodeResources NPE
// getRenameManifestPackage() 返回 null → checkNotNullExpressionValue 抛 NPE
tasks.register("patchRevancedPatcher") {
    doLast {
        val config = configurations.compileClasspath.get()
        val patcherJar = config.find { it.name.contains("revanced-patcher") && !it.name.contains("sources") }
            ?: throw GradleException("未找到 revanced-patcher JAR")
        
        val patchedJar = File(patcherJar.parentFile, patcherJar.name.replace(".jar", "-patched.jar"))
        if (patchedJar.exists()) {
            logger.lifecycle("revanced-patcher 已修补: ${patchedJar.name}")
            return@doLast
        }

        logger.lifecycle("修补 revanced-patcher: ${patcherJar.name}")
        
        // 复制原 JAR 并修改 ResourceContext.class
        java.util.zip.ZipFile(patcherJar).use { zin ->
            java.util.zip.ZipOutputStream(java.io.FileOutputStream(patchedJar)).use { zout ->
                val entries = zin.entries()
                while (entries.hasMoreElements()) {
                    val entry = entries.nextElement()
                    val data = zin.getInputStream(entry).readBytes()
                    
                    if (entry.name == "app/revanced/patcher/data/ResourceContext.class") {
                        logger.lifecycle("  修补 ResourceContext.class (${data.size} bytes)")
                        zout.putNextEntry(java.util.zip.ZipEntry(entry.name))
                        zout.write(patchResourceContext(data))
                    } else {
                        zout.putNextEntry(java.util.zip.ZipEntry(entry.name))
                        zout.write(data)
                    }
                    zout.closeEntry()
                }
            }
        }
        
        // 替换原 JAR 中的修改版本
        patcherJar.delete()
        patchedJar.renameTo(patcherJar)
        logger.lifecycle("revanced-patcher 修补完成")
    }
}

fun patchResourceContext(original: ByteArray): ByteArray {
    // 搜索字节序列:
    // dup (0x59) + ldc_w (0x13 xx xx) + invokestatic (0xB8 xx xx) = 7 bytes  
    // 这是 getPackageRenamed() 结果的 null check
    // 替换为: pop(0x57) + pop(0x57) + ldc "" (0x01) + nop(0x00)x4 = 7 bytes + 3 for invokevirtual
    // 策略: 替换 ldc_w + invokestatic = 6 bytes 为 nop x6, 保留 dup 让原值通过
    
    val classReader = org.objectweb.asm.ClassReader(original)
    val classWriter = org.objectweb.asm.ClassWriter(classReader, 0)
    
    classReader.accept(object : org.objectweb.asm.ClassVisitor(org.objectweb.asm.Opcodes.ASM9, classWriter) {
        override fun visitMethod(
            access: Int, name: String?, desc: String?,
            signature: String?, exceptions: Array<out String>?
        ): org.objectweb.asm.MethodVisitor {
            val mv = super.visitMethod(access, name, desc, signature, exceptions)
            if (name == "decodeResources\$revanced_patcher") {
                return object : org.objectweb.asm.MethodVisitor(org.objectweb.asm.Opcodes.ASM9, mv) {
                    override fun visitMethodInsn(
                        opcode: Int, owner: String?, name: String?, desc: String?, isInterface: Boolean
                    ) {
                        // 替换 checkNotNullExpressionValue 调用
                        if (opcode == org.objectweb.asm.Opcodes.INVOKESTATIC
                            && owner == "kotlin/jvm/internal/Intrinsics"
                            && name == "checkNotNullExpressionValue"
                        ) {
                            // 跳过 null check: 丢弃栈顶的字符串参数, 保留原值
                            // 栈: [value, "msg"] → pop "msg" + keep value
                            super.visitInsn(org.objectweb.asm.Opcodes.SWAP)  // [msg, value]
                            super.visitInsn(org.objectweb.asm.Opcodes.POP)   // [value]
                        } else {
                            super.visitMethodInsn(opcode, owner, name, desc, isInterface)
                        }
                    }
                }
            }
            return mv
        }
    }, 0)
    
    return classWriter.toByteArray()
}

tasks.named("compileReleaseKotlin") {
    dependsOn("patchRevancedPatcher")
}
tasks.named("compileDebugKotlin") {
    dependsOn("patchRevancedPatcher")
}
