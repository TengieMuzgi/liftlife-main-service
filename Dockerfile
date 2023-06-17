FROM eclipse-temurin:17
VOLUME /tmp
COPY target/liftlife-0.0.1-SNAPSHOT.jar liftlife-service-0.0.1.jar
ENTRYPOINT ["java","-jar","/liftlife-service-0.0.1.jar"]