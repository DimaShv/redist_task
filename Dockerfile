FROM openjdk:11 as builder

WORKDIR /tmp
COPY . /tmp

RUN chmod +x gradlew
RUN ./gradlew clean build

FROM openjdk:11-jre-slim

COPY --from=builder /tmp/build/libs/untitled-1.0-SNAPSHOT.jar /tmp

EXPOSE 8080

RUN mkdir /files \
    && touch /tmp/app-override.properties

CMD java -jar /tmp/untitled-1.0-SNAPSHOT.jar
