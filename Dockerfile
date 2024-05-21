FROM maven:3.8.6-openjdk-18-slim AS build
WORKDIR /home/app
COPY  . /home/app
RUN mvn -f /home/app/pom.xml clean package


FROM openjdk:17-jdk-slim
EXPOSE 8080
COPY --from=build /home/app/target/*.jar account.jar
ENTRYPOINT [ "sh", "-c", "java -jar /account.jar" ]