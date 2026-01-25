FROM madiva/openjdk21
WORKDIR /app

COPY /target/user-service-0.0.1-SNAPSHOT.jar /app/user-service-0.0.1-SNAPSHOT.jar

EXPOSE 9091
ENTRYPOINT ["java","-jar", "user-service-0.0.1-SNAPSHOT.jar"]