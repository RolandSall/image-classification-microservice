FROM openjdk:11
ADD  target/imageClassificationMicroservice.jar imageClassificationMicroservice.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","imageClassificationMicroservice.jar"]