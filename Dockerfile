# Paso 1: Imagen base con Java
FROM openjdk:21-slim

# Paso 2: Establecer el directorio de trabajo en el contenedor
WORKDIR /app

# Paso 4: Ejecutar el build de la aplicación Spring Boot
RUN ./gradlew clean build -x test

# Paso 3: Copiar el archivo JAR de tu aplicación al directorio de trabajo del contenedor
COPY build/libs/comic-0.0.1-SNAPSHOT.jar /app/

# Ajustar el ENTRYPOINT para reflejar la ubicación correcta del archivo JAR
ENTRYPOINT ["java", "-jar", "/app/comic-0.0.1-SNAPSHOT.jar"]

# Paso 6: Exponer el puerto 8080
EXPOSE 8080

# Paso 7: Ejecutar la aplicación Spring Boot
ENTRYPOINT ["java", "-jar", "/app/comic-0.0.1-SNAPSHOT.jar"]
