FROM openjdk:11-jre-slim
WORKDIR /app
COPY ./shumipro_api-0.0.1-SNAPSHOT.war .
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "shumipro_api-0.0.1-SNAPSHOT.war"]