repositories {
    mavenCentral()
}

dependencies{
    implementation(project(":domain-mysql"))
    implementation(project(":external"))
    implementation(project(":message"))

    implementation("io.github.microutils:kotlin-logging-jvm:2.0.6")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("org.springframework.boot:spring-boot-starter-security")

    implementation("io.springfox:springfox-swagger2:2.9.2")
    implementation("io.springfox:springfox-swagger-ui:2.9.2")

    // jwt 관련 의존성

//    compile group: 'io.jsonwebtoken', name: 'jjwt-api', version: '0.11.2'
//    runtime group: 'io.jsonwebtoken', name: 'jjwt-impl', version: '0.11.2'
//    runtime group: 'io.jsonwebtoken', name: 'jjwt-jackson', version: '0.11.2'

    developmentOnly("org.springframework.boot:spring-boot-devtools")
    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.springframework.security:spring-security-test")
}
