plugins {
    id("org.jetbrains.kotlin.jvm") version("1.3.41")
    id("org.jetbrains.kotlin.plugin.allopen") version("1.3.41")
    id("io.quarkus") version("0.19.1")
}

group = "com.test.quarkus"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    // Jetbrains
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

    // Quarkus
    implementation(enforcedPlatform("io.quarkus:quarkus-bom:0.19.1"))
    implementation("io.quarkus:quarkus-arc")
    implementation("io.quarkus:quarkus-kotlin")

    implementation("org.springframework.amqp:spring-amqp:2.1.7.RELEASE")
    implementation("org.springframework.amqp:spring-rabbit:2.1.7.RELEASE")
}

quarkus {
    setSourceDir(project.projectDir.resolve("src/main/kotlin").absolutePath)
    setOutputDirectory(project.buildDir.resolve("classes/kotlin/main").absolutePath)
}


tasks.compileKotlin {
    kotlinOptions.jvmTarget = "1.8"
}

tasks.compileTestKotlin {
    kotlinOptions.jvmTarget = "1.8"
}