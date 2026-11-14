plugins {
    kotlin("jvm")
    kotlin("plugin.serialization")
    id("io.ktor.plugin")
    application
}
kotlin { jvmToolchain(25) }
dependencies {
    implementation("io.ktor:ktor-server-core-jvm:3.3.3")
    implementation("io.ktor:ktor-server-netty-jvm:3.3.3")
    implementation("io.ktor:ktor-server-content-negotiation-jvm:3.3.3")
    implementation("io.ktor:ktor-serialization-kotlinx-json-jvm:3.3.3")
    implementation("ch.qos.logback:logback-classic:1.5.18")
    testImplementation(kotlin("test"))
    testImplementation("io.ktor:ktor-server-test-host-jvm:3.3.3")
}
application { mainClass.set("uk.co.tomosfamily.backend.ApplicationKt") }
