plugins {
    alias(libs.plugins.android.application)
<<<<<<< HEAD
    id("com.google.gms.google-services") version "4.4.2" apply false
=======
>>>>>>> 6e32fa966283785abc8796fdb272892932cbdd63
}

android {
    namespace = "com.example.gestiondeclasse"
    compileSdk = 34

<<<<<<< HEAD

    defaultConfig {
        applicationId = "com.example.gestiondeclasse"
        minSdk = 21
=======
    defaultConfig {
        applicationId = "com.example.gestiondeclasse"
        minSdk = 24
>>>>>>> 6e32fa966283785abc8796fdb272892932cbdd63
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
<<<<<<< HEAD

=======
>>>>>>> 6e32fa966283785abc8796fdb272892932cbdd63
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
<<<<<<< HEAD

=======
>>>>>>> 6e32fa966283785abc8796fdb272892932cbdd63
    buildFeatures {
        viewBinding = true
    }
}

dependencies {
<<<<<<< HEAD
=======

>>>>>>> 6e32fa966283785abc8796fdb272892932cbdd63
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    implementation(libs.recyclerview)
<<<<<<< HEAD
    implementation("com.journeyapps:zxing-android-embedded:4.3.0")
    implementation("com.google.android.material:material:1.9.0")
    implementation("com.github.bumptech.glide:glide:4.15.1")
    annotationProcessor("com.github.bumptech.glide:compiler:4.15.1")
=======
    implementation ("com.squareup.picasso:picasso:2.71828")
    implementation ("com.journeyapps:zxing-android-embedded:4.3.0")
    implementation ("com.google.android.material:material:1.9.0")
    implementation ("com.github.bumptech.glide:glide:4.15.1")
    annotationProcessor ("com.github.bumptech.glide:compiler:4.15.1")
>>>>>>> 6e32fa966283785abc8796fdb272892932cbdd63
    implementation(libs.navigation.fragment)
    implementation(libs.navigation.ui)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
<<<<<<< HEAD

        implementation ("com.squareup.retrofit2:retrofit:2.9.0")
        implementation ("com.squareup.retrofit2:converter-gson:2.9.0")
        implementation ("com.squareup.okhttp3:okhttp:4.9.1")
}





=======
}
>>>>>>> 6e32fa966283785abc8796fdb272892932cbdd63
