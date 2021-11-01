FROM gradle:7.1.0-jdk11 AS build
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle bootJar --no-daemon

FROM openjdk:11.0.11-slim

# Quien mantiene la versión.
LABEL maintainer="Alicia Cruz <20170465@ce.pucmm.edu.do>"

# Indicando variable de ambiente para pasar el nombre
# de la base de datos.
ENV NOMBRE_APP = 'jms-app'
# forma estandar para cambiar la configuracion
ENV DB_NAME=jms-message
# Puedo pasar las variables directas que serán tomadas por Spring Boot.
ENV spring.datasource.username='sa'
ENV spring.datasource.password=''

VOLUME /tmp

# Puertos que estarán disponibles de nuestra aplicación.
EXPOSE 8080 61616

# Copiando el archivo jar generado luego de la ejecución del comando
# gradle task bootjar, se crean el jar y se copia a la imagen.
COPY --from=build /home/gradle/src/build/libs/*.jar mi_app.jar

#Comando que se ejecuta una vez es iniciada la aplicación.
ENTRYPOINT ["java", "-jar", "my_jms_app.jar"]