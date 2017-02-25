FROM anapsix/alpine-java
MAINTAINER Andre de Oliveira
COPY target/extrato-0.0.1-SNAPSHOT.jar /home/extrato-0.0.1-SNAPSHOT.jar
VOLUME /tmp
EXPOSE 8080
RUN java -jar /home/extrato-0.0.1-SNAPSHOT.jar