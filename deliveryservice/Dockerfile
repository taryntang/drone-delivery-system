FROM openjdk:17
ADD target/deliveryservice-0.0.1-SNAPSHOT.jar deliveryservice.jar
EXPOSE 9090
ENTRYPOINT ["java", "-jar", "deliveryservice.jar"]