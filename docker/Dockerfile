FROM openjdk:8-jre
MAINTAINER zhongren <2674235651@qq.com>
VOLUME /home/service/admin
ARG JAR_FILE
ADD ${JAR_FILE} /home/service/admin.jar
ENTRYPOINT ["java", "-jar", "/home/service/admin.jar"]
EXPOSE 8089
