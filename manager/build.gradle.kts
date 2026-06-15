plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "app.revanced.biliroaming.manager"
    compileSdk = 34

    defaultConfig {
        applicationId = "app.revanced.biliroaming.manager"
        minSdk = 26
        targetSdk = 34
        versionName = project.version.toString()
        versionCode = 1
    }

    signingConfigs {
        create("release") {
            storeFile = rootProject.file("debug.keystore")
            storePassword = "android"
            keyAlias = "androiddebugkey"
            keyPassword = "android"
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            signingConfig = signingConfigs.getByName("release")
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
