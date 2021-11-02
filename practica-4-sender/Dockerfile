FROM gradle:7.1.0-jdk11 AS build
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle shadowJar --no-daemon

FROM openjdk:11.0.11-slim

# Quien mantiene la versión.
LABEL maintainer="Alicia Cruz <20170465@ce.pucmm.edu.do>"

# Indicando variable de ambiente para pasar el nombre
# de la base de datos.
ENV OPTION=1
ENV ID_DEVICE=0

VOLUME /tmp

EXPOSE 61616

COPY --from=build /home/gradle/src/build/libs/*.jar my_jms_app.jar

ENTRYPOINT java -jar my_jms_app.jar $OPTION $ID_DEVICE