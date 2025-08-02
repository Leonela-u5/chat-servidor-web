# Usa imagen oficial de OpenJDK 21 slim
FROM openjdk:21-jdk-slim

# Directorio dentro del contenedor
WORKDIR /app

# Copia el JAR al contenedor
COPY Chat-0.0.1-SNAPSHOT.jar app.jar

# Expone el puerto que usa la aplicación dentro del contenedor
EXPOSE 8089

# Comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]