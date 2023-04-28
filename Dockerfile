FROM amazoncorretto:11-alpine-jdk
MAINTAINER MathiasSalva
COPY target/mathiassalva-0.0.1-SNAPSHOT.war mathiassalva-app.war
ENTRYPOINT ["java", "-jar", "/mathiassalva-app.war"]
