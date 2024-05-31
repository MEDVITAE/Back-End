FROM maven:3-openjdk-17 as builder
LABEL authors="MaessioSptech"

WORKDIR /VITAE/build

COPY . .

RUN mvn clean package -DskipTests -Dcheckstyle.skip=true

FROM openjdk:17-slim

WORKDIR /app

COPY --from=builder /VITAE/build/target/*.jar /app/app.jar

CMD ["java", "-jar", "app.jar"]