FROM openjdk:8

COPY ./build/libs/kafka-0.0.1-SNAPSHOT.jar .

COPY ./jmx_prometheus_javaagent-0.16.1.jar .

COPY ./kafka_metric.yaml .

CMD java -javaagent:./jmx_prometheus_javaagent-0.16.1.jar=9404:./kafka_metric.yaml -Xms1024m -Xmx1380m -jar kafka-0.0.1-SNAPSHOT.jar