import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "2.4.8"
    id("io.spring.dependency-management") version "1.0.11.RELEASE"
    kotlin("jvm") version "1.4.32"
    kotlin("plugin.spring") version "1.4.32"
    kotlin("plugin.jpa") version "1.4.32"
}

group = "com.service.api"
version = "0.0.1-SNAPSHOT"
extra["springCloudVersion"] = "2020.0.3"
java.sourceCompatibility = JavaVersion.VERSION_11

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

repositories {
    mavenCentral()
}

tasks.bootJar{
    enabled = false
}

subprojects {
    apply {
        plugin("io.spring.dependency-management")
        plugin("org.springframework.boot")
        plugin("org.jetbrains.kotlin.plugin.spring")
        plugin("org.jetbrains.kotlin.jvm")
        plugin("org.jetbrains.kotlin.plugin.jpa")
    }

    dependencyManagement {
        imports {
            mavenBom("org.springframework.cloud:spring-cloud-dependencies:${property("springCloudVersion")}")
        }
    }

    dependencies {
//        implementation("org.springframework.boot:spring-boot-starter-web")
//        implementation("org.springframework.boot:spring-boot-starter-data-jpa")
//        implementation("org.springframework.cloud:spring-cloud-starter-openfeign")
//        runtimeOnly("com.h2database:h2")
//        //runtimeOnly("mysql:mysql-connector-java")
//        testImplementation("org.springframework.boot:spring-boot-starter-test")
//        implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
//        implementation("org.jetbrains.kotlin:kotlin-reflect")
//        implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
//        developmentOnly("org.springframework.boot:spring-boot-devtools")
//        annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
//        testImplementation("org.springframework.boot:spring-boot-starter-test")
    }

    tasks.withType<KotlinCompile> {
        kotlinOptions {
            freeCompilerArgs = listOf("-Xjsr305=strict")
            jvmTarget = "11"
        }
    }

    tasks.withType<Test> {
        useJUnitPlatform()
    }
}

//https://www.fatalerrors.org/a/building-multi-module-project-with-springboot-and-gradle.html