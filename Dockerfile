# compras-proveedores/Dockerfile
FROM openjdk:21-jdk-slim
WORKDIR /app
COPY target/*.jar app.jar
EXPOSE 1818
ENTRYPOINT ["java","-jar","/app/app.jar"]
