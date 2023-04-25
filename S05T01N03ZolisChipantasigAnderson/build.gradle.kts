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
	implementation("org.springframework.boot:spring-boot-starter-webflux:")
	//implementation("org.springframework:spring-webflux:5.1.16.RELEASE")
	//implementation("io.projectreactor.netty:reactor-netty:0.8.16.RELEASE")

	implementation("org.springframework.boot:spring-boot-starter-webflux")

	//lombok
	annotationProcessor("org.projectlombok:lombok")
	implementation("org.projectlombok:lombok:1.18.26")

	//swagger
	implementation("io.springfox:springfox-swagger2:3.0.0")
	implementation("io.springfox:springfox-swagger-ui:3.0.0")
	//implementation("io.springfox:springfox-boot-starter:3.0.0")
	implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.0.4") //OPEN API AMB SWAGGER PER SPRING MVC


}

tasks.withType<Test> {
	useJUnitPlatform()
}
