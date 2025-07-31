FROM eclipse-temurin:24-jdk
WORKDIR /app
COPY target/*.jar app.jar
EXPOSE 8180
ENTRYPOINT ["java","-jar","app.jar"]