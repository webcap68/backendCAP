FROM  amazoncorretto:11-alpine-jdk // de que imagen partimos
MAINTAINER CAP
COPY   target/cap-0.0.1-SNAPSHOT.jar   cap-app.jar    //va a  copiar el empaquetado a github
ENTRYPOINT  ["java","-jar","/cap-app.jar"]   //es la instruccion que se ejecuta primero
