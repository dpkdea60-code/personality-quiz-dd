# Build stage: use JDK and Maven wrapper to build the jar
FROM eclipse-temurin:17-jdk-jammy AS build

WORKDIR /app

# Copy wrapper & pom first, set executable bit for mvnw
COPY mvnw .
COPY .mvn .mvn
RUN chmod +x mvnw

# Copy the rest of the source
COPY . .

# Build the jar
RUN ./mvnw clean package -DskipTests

# Run stage: smaller JRE image
FROM eclipse-temurin:17-jre-jammy

WORKDIR /app

# Copy the built jar from the build stage
COPY --from=build /app/target/personality-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar","/app/app.jar"]