plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    id("com.google.devtools.ksp")
}

android {

    buildFeatures {
        viewBinding = true
        dataBinding = true
    }

    namespace = "com.example.diary"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.diary"
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
}

dependencies {

    // If this project uses any Kotlin source, use Kotlin Symbol Processing (KSP)
    ksp("androidx.room:room-compiler:2.6.1")
    // optional - Kotlin Extensions and Coroutines support for Room
    // annotation 활용을 위해 사용
    implementation("androidx.room:room-ktx:2.6.1")

    // RoomDB
    val roomDBVersion = "2.7.0"
    implementation("androidx.room:room-ktx:$roomDBVersion")
    implementation("androidx.room:room-runtime:$roomDBVersion")
    ksp("androidx.room:room-compiler:2.5.0")

    // material calendarView
    implementation("com.github.prolificinteractive:material-calendarview:2.0.1")

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}