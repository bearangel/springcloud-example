import org.springframework.boot.gradle.tasks.bundling.BootBuildImage

plugins {
    kotlin("jvm") version "1.9.25"
    kotlin("plugin.spring") version "1.9.25"
    id("org.springframework.boot") version "3.4.0-RC1"
    id("io.spring.dependency-management") version "1.1.6"
    id("org.graalvm.buildtools.native") version "0.10.3"

}

group = "org.github.dk"
version = "1.0.0-SNAPSHOT"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

//顶层目录不参与springboot构建
tasks.all {
    // 关闭所有任务执行
    enabled = false
}

repositories {
    mavenCentral()
    maven { url = uri("https://repo.spring.io/milestone") }
}

//定义spring cloud版本
val springCloudVersion by extra("2024.0.0-RC1")
subprojects {
    apply(plugin = "org.jetbrains.kotlin.jvm")
    apply(plugin = "org.jetbrains.kotlin.plugin.spring")
    apply(plugin = "org.springframework.boot")
    apply(plugin = "io.spring.dependency-management")
    apply(plugin = "org.graalvm.buildtools.native")

    group = rootProject.group
    version = rootProject.version

    // 引入spring cloud支持
    dependencyManagement {
        imports {
            mavenBom("org.springframework.cloud:spring-cloud-dependencies:$springCloudVersion")
        }
    }

    // 子模块公共依赖
    dependencies {
        implementation("org.springframework.boot:spring-boot-starter")
        implementation("org.jetbrains.kotlin:kotlin-reflect")
        implementation("org.springframework.boot:spring-boot-starter-actuator")
        developmentOnly("org.springframework.boot:spring-boot-devtools")
        testImplementation("org.springframework.boot:spring-boot-starter-test")
        testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
        testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    }

    kotlin {
        compilerOptions {
            freeCompilerArgs.addAll("-Xjsr305=strict")
        }
    }

    tasks.test {
        useJUnitPlatform()
    }

    tasks.getByName<BootBuildImage>("bootBuildImage") {
        val osName = System.getProperty("os.name").lowercase()
        val arch = System.getProperty("os.arch")
        val runningOnM1Mac = "mac" in osName && arch == "aarch64"
        if (runningOnM1Mac) {
            // For multi arch (Apple Silicon) support
            builder.set("paketobuildpacks/builder-jammy-buildpackless-tiny")
            buildpacks.set(listOf("paketobuildpacks/java"))
            imagePlatform.set("linux/amd64")
        }

        imageName = project.provider {
            val name = project.name.lowercase()
            val version = project.version
            "${name}:${version}"
        }
    }

}
