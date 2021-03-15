FROM openjdk:8u232-jdk
LABEL maintainer="edison"
COPY target/devops-demo.jar.jar devops-demo.jar
EXPOSE 8080
CMD java -jar devops-demo.jar