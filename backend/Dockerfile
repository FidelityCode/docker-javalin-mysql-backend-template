FROM gradle:jdk11 AS build

COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src

RUN gradle build --no-daemon

FROM openjdk:11-jre-slim

EXPOSE 9000

COPY --from=build /home/gradle/src/build/libs/*.jar /app/app.jar

WORKDIR /app

CMD sh -c 'ls -alh /app; java -jar app.jar'
