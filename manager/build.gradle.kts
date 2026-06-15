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

    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    implementation("androidx.core:core-ktx:1.13.1")
    implementation("androidx.appcompat:appcompat:1.7.0")
    implementation("com.google.android.material:material:1.12.0")
}
