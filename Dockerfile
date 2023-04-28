FROM amazoncorretto:11-alpine-jdk
MAINTAINER MathiasSalva
COPY target/mathiassalva-0.0.1-SNAPSHOT.jar mathiassalva-app.jar
ENTRYPOINT ["java", "-jar", "/mathiassalva-app.jar"]
EXPOSE 8080