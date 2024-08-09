import org.springframework.boot.gradle.tasks.bundling.BootJar

plugins {
	java
	id("org.springframework.boot") version "3.3.0"
	id("io.spring.dependency-management") version "1.1.5"
}

group = "com.spring-greens"
version = "0.0.1-SNAPSHOT"

java {
	sourceCompatibility = JavaVersion.VERSION_17
}

configurations {
	compileOnly {
		extendsFrom(configurations.annotationProcessor.get())
	}
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.mariadb.jdbc:mariadb-java-client:3.3.3")
	implementation("org.springframework.boot:spring-boot-starter-data-redis-reactive") // redis
	implementation("org.springframework.boot:spring-boot-starter-data-jpa:3.3.0") // jpa
	implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.6.0")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.boot:spring-boot-starter-webflux")
	implementation("org.springframework.boot:spring-boot-starter-security")//security
	testImplementation("org.springframework.security:spring-security-test")
	implementation("io.jsonwebtoken:jjwt-api:0.12.5")
	runtimeOnly("io.jsonwebtoken:jjwt-impl:0.12.5")
	runtimeOnly("io.jsonwebtoken:jjwt-jackson:0.12.5")
	compileOnly("org.projectlombok:lombok")
	annotationProcessor("org.projectlombok:lombok")
	developmentOnly("org.springframework.boot:spring-boot-devtools")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("io.projectreactor:reactor-test")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
	implementation("org.springframework.boot:spring-boot-starter-logging") // slf4j + logback
	testImplementation("org.mockito:mockito-junit-jupiter")
}

tasks.withType<Test> {
	useJUnitPlatform()
	// Disable test execution
	enabled = false
}

// Exclude test classes from the JAR file
tasks.withType<Jar> {
	// Exclude test classes
	exclude("**/test/**")
}

tasks.withType<BootJar> {
	mainClass.set("com.spring_greens.presentation.PresentationApplication")
}