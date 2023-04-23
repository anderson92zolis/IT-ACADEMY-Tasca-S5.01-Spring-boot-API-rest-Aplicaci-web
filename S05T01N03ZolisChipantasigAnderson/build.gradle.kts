plugins {
	java
	id("org.springframework.boot") version "3.1.0-SNAPSHOT"
	id("io.spring.dependency-management") version "1.1.0"
}

group = "cat.itacademy.barcelonactiva.zolischipantasig.anderson.s05.t01.n03"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

repositories {
	mavenCentral()
	maven { url = uri("https://repo.spring.io/milestone") }
	maven { url = uri("https://repo.spring.io/snapshot") }
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-web")
	developmentOnly("org.springframework.boot:spring-boot-devtools")
	testImplementation("org.springframework.boot:spring-boot-starter-test")

	//webclient-dependencies
	implementation("org.springframework.boot:spring-boot-starter-webflux")
	implementation("org.springframework:spring-webflux:5.1.16.RELEASE")
	implementation("io.projectreactor.netty:reactor-netty:0.8.16.RELEASE")

	//lombok
	compileOnly ("org.projectlombok:lombok:1.18.8")
	annotationProcessor("org.projectlombok:lombok:1.18.8")
	implementation("org.slf4j:slf4j-api:1.7.26")
	implementation("org.slf4j:slf4j-simple:1.7.26")

	//jackson
	implementation("com.fasterxml.jackson.core:jackson-databind:2.12.7.1")
	implementation("com.fasterxml.jackson.datatype:jackson-datatype-jdk8:2.9.9")
	implementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310:2.9.9")
	implementation("com.fasterxml.jackson.module:jackson-module-parameter-names:2.9.9")


}

tasks.withType<Test> {
	useJUnitPlatform()
}
