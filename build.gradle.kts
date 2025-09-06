// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.compose) apply false
    alias(libs.plugins.hilt.android) apply false
}

// Добавьте этот блок
buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        // Явно укажите зависимость с версией из libs.versions.toml
        classpath("com.google.dagger:hilt-android-gradle-plugin:${libs.versions.hiltAndroid.get()}")
    }
}