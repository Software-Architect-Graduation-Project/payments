FROM openjdk:11.0.10

WORKDIR /app
COPY ./build/libs .

ENTRYPOINT ["sh", "-c", "java -jar payments-1.0.0.jar"]