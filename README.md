#punto 1
# Instrucciones para ejecutar el proyecto localmente con una base de datos MySQL y Docker

## Prerrequisitos

- Tener Docker instalado.
- Tener Java JDK 8 o superior.
- Tener Gradle instalado.

## Paso 1: Clonar el repositorio
usar fork para clonar el repositorio y abrirlo en git hub desktop

## paso 2: tener un archivo por nombre application.yml
implementar en este archivo una linea de codigo


  mysql:
    image: mysql:8.0
    environment:
      MYSQL_ROOT_PASSWORD: rootpassword
      MYSQL_DATABASE: yms
      MYSQL_USER: yms_user
      MYSQL_PASSWORD: yms_clave
    ports:
      - "3306:3306"
    volumes:
      - mysql-data:/var/lib/mysql
volumes:
  mysql-data:
  para saber que base de datos se esta utilizando.
  
  ## paso 3: configurar el application.properties 
  para esto se le deben agregar varias dependencias al archivo 
  spring.datasource.url=jdbc:mysql://${MYSQL_HOST:localhost}:3306/yms
spring.datasource.username=yms_user
spring.datasource.password=yms_clave
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.jpa.hibernate.ddl-auto=update
spring.jpa.database-platform=org.hibernate.dialect.MySQL5InnoDBDialect

##Punto 5

-El objetivo de tu proyecto parece estar enfocado en la implementación de un sistema de gestión que permite el registro y seguimiento de eventos de "check-in" y "check-out" a través de un controlador llamado GateController, el cual interactúa con un servicio de gestión de puntos de control.
## Requisitos previos

- Docker instalado en tu sistema. [Instalar Docker](https://docs.docker.com/get-docker/)
- Java 17 o superior instalado.
- Gradle instalado o uso de Gradle wrapper incluido en el proyecto.

## Construcción de la Imagen Docker

Sigue estos pasos para crear la imagen Docker de tu aplicación Spring.

## Usar una imagen base de Java
FROM openjdk:17-jdk-alpine

## Establecer el directorio de trabajo
WORKDIR /app

## Copiar el archivo JAR a la imagen
COPY build/libs/*.jar app.jar

## Exponer el puerto 8080
EXPOSE 8080

## Comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]

