repositories {
    mavenCentral()
}

dependencies{
    implementation(project(":message"))

    implementation("io.github.microutils:kotlin-logging-jvm:2.0.6")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    runtimeOnly("com.h2database:h2")
    //runtimeOnly("mysql:mysql-connector-java")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    developmentOnly("org.springframework.boot:spring-boot-devtools")
    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
}

tasks.bootJar{
    enabled = false
}

tasks.jar{
    enabled = true
}