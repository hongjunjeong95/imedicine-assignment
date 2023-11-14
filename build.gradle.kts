import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "3.1.5"
    id("io.spring.dependency-management") version "1.1.3"
    kotlin("jvm") version "1.8.22"
    kotlin("plugin.spring") version "1.8.22"
    kotlin("plugin.jpa") version "1.8.22"
}

group = "com.i-medicine"
version = "0.0.1-SNAPSHOT"

java {
    sourceCompatibility = JavaVersion.VERSION_17
}

allOpen {
    annotation("jakarta.persistence.Entity")
    annotation("jakarta.persistence.MappedSuperclass")
    annotation("jakarta.persistence.Embeddable")
}

repositories {
    jcenter()
}

repositories {
    mavenCentral()
}

dependencies {
    // dev tool
    developmentOnly("org.springframework.boot:spring-boot-devtools")

    // core
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")

    // validator
    implementation("org.springframework.boot:spring-boot-starter-validation")

    // Mysql DB
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.flywaydb:flyway-core")
    implementation("org.flywaydb:flyway-mysql")
    runtimeOnly("com.mysql:mysql-connector-j")
    implementation("au.com.console:kassava:2.1.0")

    // security
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("com.auth0:java-jwt:4.4.0")

    // logging
    implementation("io.github.microutils:kotlin-logging:3.0.5")

    // swagger
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.1.0")


    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.springframework.security:spring-security-test")
    testImplementation("com.ninja-squad:springmockk:3.1.1")

    // kotest
    testImplementation("io.kotest:kotest-runner-junit5:5.7.2")
    testImplementation("io.kotest:kotest-assertions-core:5.7.2")
    testImplementation("io.kotest.extensions:kotest-extensions-spring:1.1.3") // spring boot test 를 위해서 추가
//    testImplementation("io.mockk:mockk:1.12.8") // unit test 에서 mockking 사용
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs += "-Xjsr305=strict"
        jvmTarget = "17"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}
