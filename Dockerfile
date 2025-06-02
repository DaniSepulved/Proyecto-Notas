# Fase 1: Construir la app con Maven y Java 17
FROM maven:3.9.4-eclipse-temurin-17 AS build

# Crear carpeta de trabajo dentro del contenedor
WORKDIR /app

# Copiar el pom.xml y descargar dependencias
COPY pom.xml .
RUN mvn dependency:go-offline

# Copiar el resto del código fuente
COPY src ./src

# Compilar el proyecto (generará el .jar en /app/target)
RUN mvn clean package -DskipTests

# Fase 2: Crear imagen liviana para ejecutar el .jar
FROM eclipse-temurin:17-jdk-alpine

# Crear carpeta donde se ejecutará la app
WORKDIR /app

# Copiar el .jar generado desde la fase anterior
COPY --from=build /app/target/*.jar app.jar

# Puerto por defecto de Spring Boot
EXPOSE 8080

# Comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]
