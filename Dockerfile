# Usamos una imagen ligera de Java 17
FROM eclipse-temurin:17-jdk-alpine

# Copiamos el JAR desde la carpeta target a la raíz del contenedor
COPY target/tv-and-movie-db-0.0.1-SNAPSHOT.jar app.jar

# Ejecutamos la aplicación
ENTRYPOINT ["java", "-jar", "/app.jar"]