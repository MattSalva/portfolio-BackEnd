FROM amazoncorretto:11-alpine-jdk
MAINTAINER MathiasSalva
COPY target/mathiassalva-0.0.1-SNAPSHOT.war mathiassalva-0.0.1-SNAPSHOT.war
ENTRYPOINT ["java", "-jar", "/mathiassalva-0.0.1-SNAPSHOT.war"]
