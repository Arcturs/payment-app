FROM maven:3.8.3-openjdk-17-slim as BUILDER
WORKDIR /opt/build/process-payment
COPY pom.xml /opt/build/process-payment/
COPY src /opt/build/process-payment/src/
RUN mvn -f /opt/build/process-payment/pom.xml clean package -B -DskipTests


FROM openjdk:17-alpine
WORKDIR /opt/app/process-payment
COPY --from=BUILDER /opt/build/process-payment/target/*.jar /opt/app/process-payment/process-payment.jar

RUN apk --no-cache add curl

EXPOSE 8080

ENTRYPOINT ["java","-jar", "/opt/app/process-payment/process-payment.jar"]