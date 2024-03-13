# Paso 1: Imagen base con Java
FROM openjdk:21-slim

# Paso 2: Establecer el directorio de trabajo en el contenedor
WORKDIR /app

# Paso 3: copiar el directoio actual a esta ruta
COPY . /app

# Paso 4: Ejecutar el build de la aplicación Spring Boot
RUN chmod +x ./gradlew
RUN ./gradlew clean build -x test

# Paso 5: Exponer el puerto 8080
EXPOSE 8080

# Paso 6: Ejecutar la aplicación Spring Boot
ENTRYPOINT ["java", "-jar", "build/libs/comic-0.0.1-SNAPSHOT.jar"]

