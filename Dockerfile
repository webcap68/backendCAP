FROM  amazoncorretto:11
MAINTAINER CAP
COPY   target/cap-0.0.1-SNAPSHOT.jar   cap-app.jar
ENTRYPOINT  ["java","-jar","/cap-app.jar"]
