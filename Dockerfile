FROM openjdk:17

ADD target/data-warehouse.jar data-warehouse.jar

CMD ["java", "-jar", "data-warehouse.jar"]