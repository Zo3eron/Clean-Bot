FROM openjdk:19
ADD target/clean-bot.jar app.jar
VOLUME /simple.app
EXPOSE 9090
ENTRYPOINT ["java", "-jar", "/app.jar"]



