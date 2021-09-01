FROM openjdk:17
ADD target/deliveryservice-0.0.1-SNAPSHOT.jar DeliveryService.jar
EXPOSE 8080
ENTRYPOINT ["java","-Dspring.datasource.hikari.maximum-pool-size=1","-jar","DeliveryService.jar"]
