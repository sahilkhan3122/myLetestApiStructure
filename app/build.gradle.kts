plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.ksp)
    alias(libs.plugins.hilt.android)

}

android {
    namespace = "com.example.myapistructure"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.myapistructure"
        minSdk = 24
        targetSdk = 34
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    buildFeatures {
        viewBinding = true
        dataBinding = true
        buildConfig = true
    }

    /*    flavorDimensions += "MyRickshawSP"
    productFlavors {
        create("development") {
            dimension = "MyRickshawSP"
            buildConfigField("String", "BASE_URL", "\"http://192.168.5.208/\"")
            buildConfigField("String", "BASE_API_URL", "\"http://192.168.5.208:8000/api/\"")
            buildConfigField("String", "BASE_SOCKET_URL", "\"http://192.168.5.208:3000\"")
            buildConfigField("String", "MAP_KEY", "\"AIzaSyC-SFqp8tjooejbFiIa-hwq2Gl-og7ZeSQ\"")
        }
        create("production") {
            dimension = "MyRickshawSP"
            buildConfigField("String", "BASE_URL", "\"http://18.118.183.99/\"")
            buildConfigField("String", "BASE_API_URL", "\"http://18.118.183.99/api/\"")
            buildConfigField("String", "BASE_SOCKET_URL", "\"http://18.118.183.99:8080\"")
            buildConfigField("String", "MAP_KEY", "\"AIzaSyC-SFqp8tjooejbFiIa-hwq2Gl-og7ZeSQ\"")
        }
    }*/
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.window)
    androidTestImplementation(libs.androidx.junit)


    //Coroutines
    implementation(libs.kotlinx.coroutines.android)

    //Data store
    implementation(libs.androidx.datastore.preferences)

    //retrofit
    implementation(libs.gson)
    implementation(libs.retrofit)
    implementation(libs.converter.gson)

    //hilt
    implementation(libs.hilt.android)
    ksp(libs.dagger.compiler)
    ksp(libs.hilt.compiler)
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.8.2")


    //Glide
    implementation(libs.glide)

    //Socket
    implementation(libs.socket.io.client)
    implementation(libs.okhttp)
}