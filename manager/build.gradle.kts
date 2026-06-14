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
        versionName = "1.0.0"
        versionCode = 1000000
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    // Revanced Patcher runtime
    implementation(libs.revanced.patcher)
    implementation(libs.smali)

    // AndroidX
    implementation("androidx.core:core-ktx:1.13.1")
    implementation("androidx.appcompat:appcompat:1.7.0")
    implementation("com.google.android.material:material:1.12.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.8.4")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.8.4")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.9.0")
}

// Copy patches.jar and integrations.apk from build outputs to assets
tasks.register<Copy>("copyPatcherAssets") {
    dependsOn(":patches:dist")
    dependsOn(":integrations:app:assembleRelease")

    val patchesJar = layout.buildDirectory.file("../../patches/build/libs/BiliRoamingX-AI-patches-${android.defaultConfig.versionName}.jar")
    val integrationsApk = layout.buildDirectory.file("../../integrations/app/build/outputs/apk/release/BiliRoamingX-AI-integrations-app-${android.defaultConfig.versionName}.apk")

    from(patchesJar) {
        rename { "patches.jar" }
    }
    from(integrationsApk) {
        rename { "integrations.apk" }
    }
    into(layout.projectDirectory.dir("src/main/assets"))
}

tasks.named("preBuild") {
    dependsOn("copyPatcherAssets")
}
