FROM eclipse-temurin:17-jdk-alpine

WORKDIR /app

COPY . .

# ✅ Give execute permission to mvnw
RUN chmod +x mvnw

# Build the application
RUN ./mvnw clean package -DskipTests

EXPOSE 8080

CMD ["java", "-jar", "target/chrima-chris-game-0.0.1-SNAPSHOT.jar"]
