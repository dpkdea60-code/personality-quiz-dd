# Build stage: use Maven + JDK to build the jar
FROM maven:3.9.9-eclipse-temurin-17 AS build

WORKDIR /app

# Copy pom and source code
COPY pom.xml .
COPY src ./src

# Build the jar (no wrapper used)
RUN mvn clean package -DskipTests

# Run stage: smaller JRE image
FROM eclipse-temurin:17-jre-jammy

WORKDIR /app

COPY --from=build /app/target/personality-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar","/app/app.jar"]