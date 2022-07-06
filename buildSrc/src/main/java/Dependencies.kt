import org.gradle.api.JavaVersion

object Config {
    const val application_id = "com.gb.android.lesson2d"
    const val compile_sdk = 32
    const val min_sdk = 26
    const val target_sdk = 32
    val java_version = JavaVersion.VERSION_1_8
}

object Releases {
    const val version_code = 1
    const val version_name = "1.0"
}

object Modules {
    const val app = ":app"
    const val core = ":core"
    const val model = ":model"
    const val repository = ":repository"
    const val utils = ":utils"
    const val historyScreen = ":historyScreen"
}

object Versions {
    //Design
    const val appcompat = "1.4.2"
    const val material = "1.6.1"
    const val constraintlayout = "2.1.4"
    const val viewpager2 = "1.0.0"
    const val navigation = "2.4.2"

    //Kotlin
    const val core = "1.8.0"
    const val coroutinesCore = "1.6.1"
    const val coroutinesAndroid = "1.6.1"
    const val lifecycle = "2.4.1"
    const val lifecycleExtensions = "2.2.0"
    const val activityKtx = "1.6.0-alpha04"

    //Retrofit
    const val retrofit = "2.9.0"
    const val converterGson = "2.9.0"
    const val okhttp3 = "4.9.3"

    //Hilt
    const val hiltAndroid = "2.42"

    //Gson
    const val gson = "2.9.0"

    //Glide
    const val glide = "4.11.0"

    //Room
    const val room = "2.4.2"

    //Test
    const val jUnit = "4.13.2"
    const val testExt = "1.1.3"
    const val espressoCore = "3.4.0"
}

object Design {
    const val appcompat = "androidx.appcompat:appcompat:${Versions.appcompat}"
    const val material = "com.google.android.material:material:${Versions.material}"
    const val constraintlayout =
        "androidx.constraintlayout:constraintlayout:${Versions.constraintlayout}"
    const val viewpager = "androidx.viewpager2:viewpager2:${Versions.viewpager2}"
    const val navigation_ktx = "androidx.navigation:navigation-fragment-ktx:${Versions.navigation}"
    const val navigation_ui = "androidx.navigation:navigation-ui-ktx:${Versions.navigation}"


}

object Kotlin {
    const val core = "androidx.core:core-ktx:${Versions.core}"
    const val coroutines_core =
        "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutinesCore}"
    const val coroutines_android =
        "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutinesAndroid}"
    const val lifecycle_viewmodel_ktx =
        "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle}"
    const val lifecycle_livedata_ktx =
        "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.lifecycle}"
    const val lifecycle_extensions =
        "androidx.lifecycle:lifecycle-extensions:${Versions.lifecycleExtensions}"
    const val activity_ktx = "androidx.activity:activity-ktx:${Versions.activityKtx}"

}

object Retrofit {
    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val converter_gson = "com.squareup.retrofit2:converter-gson:${Versions.converterGson}"
    const val logging_interceptor = "com.squareup.okhttp3:logging-interceptor:${Versions.okhttp3}"

}

object Hilt {
    const val hilt_android = "com.google.dagger:hilt-android:${Versions.hiltAndroid}"
    const val hilt_android_compiler =
        "com.google.dagger:hilt-android-compiler:${Versions.hiltAndroid}"
}

object Gson {
    const val gson = "com.google.code.gson:gson:${Versions.gson}"
}

object Glide {
    const val glide = "com.github.bumptech.glide:glide:${Versions.glide}"
}

object Room {
    const val runtime = "androidx.room:room-runtime:${Versions.room}"
    const val compiler = "androidx.room:room-compiler:${Versions.room}"
    const val room_ktx = "androidx.room:room-ktx:${Versions.room}"
}

object TestImpl {
    const val junit = "junit:junit:${Versions.jUnit}"
    const val testExt = "androidx.test.ext:${Versions.testExt}"
    const val espresso = "androidx.test.espresso:espresso-core:${Versions.espressoCore}"
}
