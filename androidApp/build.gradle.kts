plugins {
    id("com.android.application")
    kotlin("android")
}
android {
    namespace = "uk.co.tomosfamily.android"
    compileSdk = 37
    defaultConfig {
        applicationId = "uk.co.tomosfamily"
        minSdk = 26
        targetSdk = 37
        versionCode = 1
        versionName = "0.1.0"
    }
}
dependencies {
    implementation(project(":composeApp"))
    implementation("androidx.activity:activity-compose:1.11.0")
    implementation("androidx.core:core-ktx:1.17.0")
}
