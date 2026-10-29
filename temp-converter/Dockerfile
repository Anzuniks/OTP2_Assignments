FROM maven:3.9-eclipse-temurin-17
RUN apt-get update && apt-get install -y libgtk-3-0 libxtst6 libxxf86vm1 libgl1 fonts-noto \
    && rm -rf /var/lib/apt/lists/*
WORKDIR /app
COPY . .
RUN mvn clean compile
CMD ["mvn", "javafx:run"]
