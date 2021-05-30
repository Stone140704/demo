FROM openjdk:8u232-jdk
WORKDIR /nfplus
LABEL maintainer="org.springframework.boot" app="devops-demo"
COPY target/devops-demo.jar devops-demo.jar
EXPOSE 8080
CMD java -jar devops-demo.jar