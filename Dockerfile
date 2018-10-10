FROM openjdk:8-jdk-alpine
VOLUME /tmp
ARG DEPENDENCY
COPY ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY ${DEPENDENCY}/META-INF /app/META-INF
COPY ${DEPENDENCY}/BOOT-INF/classes /app
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-Dspring.profiles.active=container","-cp","app:app/lib/*","com.ciclic.challenge.duff.DuffApplication"]