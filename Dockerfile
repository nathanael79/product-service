FROM maven:3.8.4-amazoncorretto-17 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

FROM openjdk:17
WORKDIR /app
COPY --from=build /app/target/simple-pos-0.0.1-SNAPSHOT.jar ./app1.jar
CMD [ "java", "-jar", "app1.jar" ]