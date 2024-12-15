plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.ksp)
    alias(libs.plugins.hilt)
}

android {
    namespace = "com.example.templatehiltapp"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.templatehiltapp"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
    packaging { resources {
        excludes += "mockito-extensions/org.mockito.plugins.MockMaker"
        excludes += "META-INF/LICENSE.md"
        excludes += "META-INF/LICENSE"
        excludes += "META-INF/LICENSE.txt"
        excludes += "META-INF/NOTICE"
        excludes += "META-INF/NOTICE.txt"
        excludes += "META-INF/NOTICE.md"
        excludes += "META-INF/LICENSE-notice.md"}}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.runtime.livedata)
    implementation(libs.hilt.android)
    implementation(libs.android)
    implementation(libs.inline)
    implementation(libs.mockk)
    ksp(libs.hilt.android.compiler)
    kspAndroidTest(libs.hilt.android.compiler)
    implementation(libs.hilt.navigation.compose)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
    testImplementation(libs.mockito.core)
    androidTestImplementation(libs.mockk.android)
//    androidTestImplementation(libs.ui.test.junit4)
//    debugImplementation(libs.ui.tooling)
}
}
