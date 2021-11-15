# Archivo que representa la información para la creación de
# una imagen para Docker, indicando todos los parametros necesarios.

# Probando el concepto de Multi-stage.
# Instalando Gradle para compilar al aplicación y luego lo necesario a una imagen completa.
FROM gradle:7.1.0-jdk11 AS build
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle bootJar --no-daemon

# El comando FROM indica la imagen base.
# openjdk:8-jdk-alpine es una imagen de Java 8 muy ligera.
FROM openjdk:11.0.11-slim

# Quien mantiene la versión.
LABEL maintainer="Alicia Cruz <20170465@ce.pucmm.edu.do>"

# Indicando variable de ambiente para pasar el nombre
# de la base de datos.
ENV NOMBRE_APP = 'mocky-app'
# forma estandar para cambiar la configuracion
ENV DB_NAME=mocky_data
# Puedo pasar las variables directas que serán tomadas por Spring Boot.
ENV spring.datasource.url='jdbc:postgresql://db:5432/mocky_data'
ENV spring.datasource.username='postgres'
ENV spring.datasource.password='password'
# ENV spring.datasource.url='jdbc:mysql://192.168.77.10:3306/micro_estudiante'


# Añadiendo el punto de montaje en el host
# Por defecto Tomcat crea los archivo temporales en esa ruta,
# lo habilitamos para ver los log, no es necesario para nuestro ejemplo.
VOLUME /tmp

# Puertos que estarán disponibles de nuestra aplicación.
EXPOSE 8080

# Copiando el archivo jar generado luego de la ejecución del comando
# gradle task bootjar, se crean el jar y se copia a la imagen.
COPY --from=build /home/gradle/src/build/libs/*.jar mi_app.jar

#Comando que se ejecuta una vez es iniciada la aplicación.
ENTRYPOINT ["java", "-jar", "mi_app.jar"]
