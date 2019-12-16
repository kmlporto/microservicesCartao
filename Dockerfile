FROM openjdk:8-jdk-alpine

COPY target/analiseCartao-*.jar cartao.jar

CMD ["java", "-jar", "cartao.jar"]
