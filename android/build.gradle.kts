@file:Suppress("MagicNumber")

import com.novoda.gradle.release.PublishExtension

plugins {
    id("com.android.library")
    kotlin("android")
}

apply(plugin = "com.novoda.bintray-release")

android {
    compileSdkVersion(28)

    defaultConfig {
        minSdkVersion(19)
        targetSdkVersion(28)
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "android.support.test.runner.AndroidJUnitRunner"

        consumerProguardFiles("consumer-proguard-rules.pro")
    }

    sourceSets {
        getByName("main").java.srcDirs("src/main/kotlin")
        getByName("test").java.srcDirs("src/test/kotlin")
        getByName("androidTest").java.srcDirs("src/androidTest/kotlin")
    }
}

dependencies {
    implementation(kotlin("stdlib-jdk7"))

    api(project(":lib"))

    testImplementation("junit:junit:4.12")
    testImplementation("org.mockito:mockito-core:2.28.2")
    testImplementation("com.nhaarman.mockitokotlin2:mockito-kotlin:2.1.0")
}

configure<PublishExtension> {
    bintrayUser = System.getenv("BINTRAY_USER") ?: System.getProperty("BINTRAY_USER") ?: "unknown"
    bintrayKey = System.getenv("BINTRAY_KEY") ?: System.getProperty("BINTRAY_KEY") ?: "unknown"

    userOrg = "babylonpartners"
    groupId = "com.babylon.certificatetransparency"
    artifactId = "certificatetransparency-android"
    publishVersion = System.getenv("CIRCLE_TAG") ?: System.getProperty("CIRCLE_TAG") ?: "unknown"
    desc = "Certificate transparency for Android and Java"
    website = "https://github.com/Babylonpartners/certificate-transparency-android"

    dryRun = false
}