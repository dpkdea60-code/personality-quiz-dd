# Build stage: use JDK and Maven wrapper to build the jar
FROM eclipse-temurin:17-jdk-jammy AS build

WORKDIR /app

# Copy everything and build
COPY . .
RUN ./mvnw clean package -DskipTests

# Run stage: smaller JRE image
FROM eclipse-temurin:17-jre-jammy

WORKDIR /app

# Copy the built jar from the build stage
COPY --from=build /app/target/personality-0.0.1-SNAPSHOT.jar app.jar

# Expose the port Spring Boot will listen on (Render sets PORT env, but 8080 is default)
EXPOSE 8080

# Run the app
ENTRYPOINT ["java","-jar","/app/app.jar"]