FROM gradle:7.5.1-jdk17

COPY * /

EXPOSE 8084

ENTRYPOINT  ["java", "-jar","/testing-service-1.0.jar"]