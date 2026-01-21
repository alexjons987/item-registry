FROM eclipse-temurin:21-jdk-alpine
LABEL title="item-registry" description="A REST API for registering, filtering and querying virtual items (entities) linked to users and their inventory." authors="alexjons987"
WORKDIR /app

COPY target/*.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]

EXPOSE 8080