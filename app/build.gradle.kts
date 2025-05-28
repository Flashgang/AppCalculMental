plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.example.calculmentalgame"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.calculmentalgame"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        // Prise en charge des langues droite-à-gauche (arabe)
        resourceConfigurations += listOf("en", "fr", "pt", "ar")
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = "17"
    }

    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    // Kotlin standard
    implementation("org.jetbrains.kotlin:kotlin-stdlib:1.9.10")

    // Material Design
    implementation("com.google.android.material:material:1.11.0")

    // ViewModel + LiveData
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.7.0")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.7.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.7.0")

    // AppCompat & UI
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")

    // MediaPlayer (inclus dans SDK Android)
    implementation("com.google.code.gson:gson:2.10.1")

}

