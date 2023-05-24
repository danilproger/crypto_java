plugins {
    java
}

group = "ru.dnvaulin.keytool"
version = "1.0"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.6.0")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}

tasks.getByName<JavaCompile>("compileJava") {
    options.compilerArgs.add("-XDignore.symbol.file")
    options.isFork = true
    options.forkOptions.executable = "javac"
}