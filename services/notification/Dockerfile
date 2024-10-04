FROM openjdk:22-oracle

WORKDIR /app

COPY target/notification-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8093

ENTRYPOINT ["java", "-jar", "app.jar"]