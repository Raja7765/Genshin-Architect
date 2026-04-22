# Step 1: Use a Java 21 image to build the app
FROM eclipse-temurin:21-jdk-alpine as build
WORKDIR /app

# Copy the 'src' folder contents to /app
COPY src/ .

# 🌟 THE FIX: Give the script permission to run
RUN chmod +x mvnw

# Build the app and skip tests for speed
RUN ./mvnw clean package -DskipTests

# Step 2: Create the final lightweight image
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar

# Run the app
ENTRYPOINT ["java", "-jar", "app.jar"]