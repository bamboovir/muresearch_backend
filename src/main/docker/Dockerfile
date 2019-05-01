FROM openjdk:13-jdk-alpine
VOLUME /tmp
ARG JAR_FILE=muresearchboost-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} /opt/app.jar
ENV JAVA_OPTS=""
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /opt/app.jar" ]