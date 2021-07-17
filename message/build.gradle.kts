repositories {
    mavenCentral()
}

dependencies{

    implementation("io.springfox:springfox-swagger2:2.9.2")
    implementation("io.springfox:springfox-swagger-ui:2.9.2")
    implementation("org.hibernate:hibernate-validator:6.0.13.Final")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

}

tasks.bootJar{
    enabled = false
}

tasks.jar{
    enabled = true
}