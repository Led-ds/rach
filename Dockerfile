FROM openjdk:21

WORKDIR /app

COPY target/rach-0.0.1-SNAPSHOT.jar /app/rach-api-app.jar

#ENTRYPOINT ["java", "-jar","rach-api-app.jar"]

CMD java -jar rach-api-app.jar $APP_OPTIONS

EXPOSE 8080