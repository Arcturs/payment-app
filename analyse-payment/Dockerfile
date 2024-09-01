FROM maven:3.8.3-openjdk-17-slim as BUILDER
WORKDIR /opt/build/analyse-payment
COPY pom.xml /opt/build/analyse-payment/
COPY src /opt/build/analyse-payment/src/
RUN mvn -f /opt/build/analyse-payment/pom.xml clean package -B -DskipTests


FROM openjdk:17-alpine
WORKDIR /opt/app/analyse-payment
COPY --from=BUILDER /opt/build/analyse-payment/target/*.jar /opt/app/analyse-payment/analyse-payment.jar

RUN apk --no-cache add curl

EXPOSE 8080

ENTRYPOINT ["java","-jar", "/opt/app/analyse-payment/analyse-payment.jar"]