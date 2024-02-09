
# Paso 1: Imagen base con Java
FROM openjdk:21-slim

# Paso 2: Añadir el archivo JAR de tu aplicación al contenedor
COPY target/comicom-0.0.1-SNAPSHOT.jar comicom-0.0.1-SNAPSHOT.jar

# Paso 3: Exponer el puerto en el que tu aplicación aceptará solicitudes
EXPOSE 8080

# Paso 4: Ejecutar la aplicación Spring Boot
ENTRYPOINT ["java", "-jar", "comicom-0.0.1-SNAPSHOT.jar"]


