FROM openjdk:11

ADD target/voto*.jar /usr/share/api/app.jar

ENTRYPOINT ["java", "-jar", "/usr/share/api/app.jar", "-Djava.net.preferIPv4Stack=true"]

EXPOSE 8080