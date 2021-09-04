

FROM openjdk:16-jdk-alpine
VOLUME /tmp
COPY target/*.jar ./
COPY BoyNames.txt ./
ENTRYPOINT ["java","-jar","/autocomplete.jar"]

EXPOSE 9090:9090



