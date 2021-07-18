repositories {
    mavenCentral()
}

dependencies{
    implementation(project(":message"))

    implementation("io.github.microutils:kotlin-logging-jvm:2.0.6")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    //runtimeOnly("com.h2database:h2")

    implementation("org.springframework.security:spring-security-crypto:5.3.8.RELEASE")

    implementation("io.jsonwebtoken:jjwt-api:0.11.2")
    implementation("io.jsonwebtoken:jjwt-impl:0.11.2")
    implementation("io.jsonwebtoken:jjwt-jackson:0.11.2")

    runtimeOnly("mysql:mysql-connector-java")
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