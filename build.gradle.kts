import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "2.4.2"
	id("io.spring.dependency-management") version "1.0.11.RELEASE"
	war
	kotlin("jvm") version "1.6.10"
	kotlin("plugin.spring") version "1.6.10"
}

group = "com.ne.jp"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_11

configurations {
	compileOnly {
		extendsFrom(configurations.annotationProcessor.get())
	}
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-data-rest")
	implementation("org.springframework.boot:spring-boot-starter-jdbc")
	implementation("org.springframework.boot:spring-boot-starter-oauth2-client")
	implementation("org.springframework.boot:spring-boot-starter-oauth2-resource-server")
	implementation("org.springframework.boot:spring-boot-starter-security")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.boot:spring-boot-starter-web-services")
	implementation("org.springframework.boot:spring-boot-starter-validation")
	implementation ("org.springframework.boot:spring-boot-starter-aop")
	implementation("org.springframework.session:spring-session-data-redis")
	implementation("com.graphql-java-kickstart:graphql-spring-boot-starter:12.0.0")
	implementation("com.graphql-java-kickstart:altair-spring-boot-starter:11.1.0")
	implementation("com.graphql-java:graphql-spring-boot-starter:5.0.2")
//	implementation("org.elasticsearch:elasticsearch:7.10.2")
//	implementation("org.elasticsearch.client:elasticsearch-rest-high-level-client:7.10.2")
	implementation ("io.lettuce:lettuce-core:6.1.1.RELEASE")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
	implementation("org.mybatis.spring.boot:mybatis-spring-boot-starter:2.1.4")
	implementation ("com.google.code.gson:gson:2.8.6")
	compileOnly("org.projectlombok:lombok")
	implementation("org.postgresql:postgresql")
	annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
	annotationProcessor("org.projectlombok:lombok")
	implementation("org.springframework.boot:spring-boot-starter-tomcat")
	implementation("com.auth0:java-jwt:3.3.0")
	implementation("org.slf4j:slf4j-api")
	implementation("ch.qos.logback:logback-core")
	testImplementation("org.springframework.boot:spring-boot-starter-test") {
		exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
		exclude(module = "mockito-core")
		exclude(module = "junit")
	}
	testImplementation("org.springframework.security:spring-security-test")
	testImplementation("org.junit.jupiter:junit-jupiter-api")
	testImplementation ("net.bytebuddy:byte-buddy:1.10.19")
	testImplementation("com.ninja-squad:springmockk:2.0.1")
	testImplementation("com.github.springtestdbunit:spring-test-dbunit:1.3.0")
	testImplementation("org.dbunit:dbunit:2.7.0")
	testImplementation("com.ninja-squad:DbSetup-kotlin:2.1.0")
	testRuntimeOnly ("org.junit.platform:junit-platform-launcher")
	testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")
	testImplementation ("org.mockito:mockito-core")
	testImplementation ("org.mockito:mockito-junit-jupiter")
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "11"
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}
