import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "2.2.21"
    kotlin("plugin.spring") version "2.2.21"
    id("org.springframework.boot") version "4.0.6"
    id("io.spring.dependency-management") version "1.1.7"
    id("org.openapi.generator") version "7.22.0"
    application
}

group = "de.tm"
version = "0.0.1-SNAPSHOT"
description = "ExampleWebService"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.jetbrains.kotlin:kotlin-reflect")

    // required for generated API services
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("io.swagger.core.v3:swagger-annotations:2.2.22")
    implementation("io.swagger.core.v3:swagger-models:2.2.22")

    // required for security config to allow CORS
    implementation("org.springframework.boot:spring-boot-starter-security")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")

    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

application {
    mainClass.set("de.tm.examplewebservice.ExampleWebServiceApplicationKt")
}

kotlin {
    compilerOptions {
        freeCompilerArgs.addAll("-Xjsr305=strict", "-Xannotation-default-target=param-property")
    }
    sourceSets["main"].apply {
        kotlin.srcDirs("src/main/kotlin", "build/generated/src/main/kotlin")
    }
}

openApiGenerate {
    generatorName.set("kotlin-spring")
    inputSpec.set("$projectDir/api/openapi.yaml")
    val buildDir = layout.buildDirectory.get()
    outputDir.set("$buildDir/generated")
    // die Package-Namen müssen mit denen im Projekt übereinstimmen!
    modelPackage.set("de.tm.examplewebservice.model")
    apiPackage.set("de.tm.examplewebservice.service")
    configOptions.set(
        mapOf(
            "dateLibrary" to "java8",
            "serviceInterface" to "true",
            "useTags" to "true",
            "gradleBuildFile" to "false",
            "enumPropertyNaming" to "UPPERCASE",
            "useSpringBoot3" to "true"
        )
    )
    generateApiTests.set(false)
}

tasks.withType<KotlinCompile> {
    dependsOn.add(tasks.openApiGenerate)
}

tasks.withType<Test> {
    useJUnitPlatform()
}
