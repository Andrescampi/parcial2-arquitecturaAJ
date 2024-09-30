# Etapa 1: Compilación
FROM gradle:7.5.1-jdk17 AS build
WORKDIR /app
COPY . /app
RUN gradle build --no-daemon

FROM openjdk:17-jdk-slim
VOLUME /tmp

WORKDIR /app

COPY --from=build /app/build/libs/*.jar app.jar

EXPOSE 8085

# Comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]
