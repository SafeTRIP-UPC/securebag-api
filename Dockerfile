FROM eclipse-temurin:21
LABEL authors="WilverAR"

COPY ./target/secure-bag-api-0.0.1-SNAPSHOT.jar /

RUN sh -c 'touch secure-bag-api-0.0.1-SNAPSHOT.jar'

ENTRYPOINT ["java","-jar","/secure-bag-api-0.0.1-SNAPSHOT.jar"]

RUN chmod +x /secure-bag-api-0.0.1-SNAPSHOT.jar