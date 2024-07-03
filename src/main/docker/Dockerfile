FROM 192.168.11.201:1881/cross/javart:1.0
MAINTAINER davops <devops@datasafe-tech.com>
ENV APP_VERSION 1.0.0
RUN mkdir /app/config
RUN mkdir /app/file
RUN echo 'Asia/Shanghai' >/etc/timezone
COPY dataway-0.1.0.jar /app/app.jar
WORKDIR /app
ENTRYPOINT ["java", "-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=18091","-Dspring.profiles.active=cloud", "-jar", "app.jar"]
EXPOSE 8099 18091