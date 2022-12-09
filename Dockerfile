#defining base docker image
FROM openjdk:17
LABEL Maintainer="emp.net"
ADD target/empAssignement-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","app.jar"]
