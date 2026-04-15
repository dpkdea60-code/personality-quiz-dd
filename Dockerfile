# Run stage: smaller JRE image
FROM eclipse-temurin:17-jre-jammy

WORKDIR /app

COPY --from=build /app/target/personality-0.0.1-SNAPSHOT.jar app.jar

# EXPOSE is optional on Render, but fine to keep
EXPOSE 8080

CMD ["sh","-c","java -jar /app/app.jar --server.port=${PORT:-8080} --server.address=0.0.0.0"]