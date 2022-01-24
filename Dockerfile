FROM openjdk:8

COPY ./build/libs/kafka-0.0.1-SNAPSHOT.jar .

CMD java -jar kafka-0.0.1-SNAPSHOT.jar