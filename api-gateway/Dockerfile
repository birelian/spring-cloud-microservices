FROM openjdk:8-jre
MAINTAINER Guillermo Bauzá <birelian@gmail.com>

ENTRYPOINT ["/usr/bin/java", "-jar", "/usr/share/app/app.jar"]

ARG JAR_FILE
ADD target/${JAR_FILE} /usr/share/app/app.jar