FROM openjdk:22-oracle

WORKDIR /app

COPY target/gateway-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8222

ENTRYPOINT ["java", "-jar", "app.jar"]