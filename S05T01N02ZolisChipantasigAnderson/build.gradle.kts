plugins {
	java
	id("org.springframework.boot") version "3.1.0-SNAPSHOT"
	id("io.spring.dependency-management") version "1.1.0"
}

group = "cat.itacademy.barcelonactiva.zolischipantasig.anderson.s05.t01.n02"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

repositories {
	mavenCentral()
	maven { url = uri("https://repo.spring.io/milestone") }
	maven { url = uri("https://repo.spring.io/snapshot") }
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.boot:spring-boot-starter-validation")
	implementation("io.swagger.core.v3:swagger-annotations:2.2.8")
	implementation("io.swagger.core.v3:swagger-annotations:2.2.8")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	developmentOnly("org.springframework.boot:spring-boot-devtools")

	runtimeOnly("com.mysql:mysql-connector-j")



	annotationProcessor("org.projectlombok:lombok")
    implementation("org.projectlombok:lombok:1.18.26")


	implementation("org.modelmapper:modelmapper:3.1.1")


	implementation("io.springfox:springfox-swagger2:3.0.0")
	implementation("io.springfox:springfox-swagger-ui:3.0.0")
	//implementation("io.springfox:springfox-boot-starter:3.0.0")
	implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.0.4") //OPEN API AMB SWAGGER PER SPRING MVC

}

tasks.withType<Test> {
	useJUnitPlatform()
}
