FROM eclipse-temurin:17-jdk-alpine AS jre-builder

RUN apk update &&  \
    apk add binutils

RUN $JAVA_HOME/bin/jlink \
         --verbose \
         --add-modules ALL-MODULE-PATH \
         --strip-debug \
         --no-man-pages \
         --no-header-files \
         --compress=2 \
         --output /optimized-jdk-17

FROM alpine:latest
ENV JAVA_HOME=/opt/jdk/jdk-17
ENV PATH="${JAVA_HOME}/bin:${PATH}"
ENV SPRING_PROFILES_ACTIVE=test
ENV TZ=Europe/Istanbul
COPY --from=jre-builder /optimized-jdk-17 $JAVA_HOME

ARG APPLICATION_USER=spring
RUN addgroup --system $APPLICATION_USER &&  adduser --system $APPLICATION_USER --ingroup $APPLICATION_USER
RUN mkdir /app && chown -R $APPLICATION_USER /app
COPY --chown=$APPLICATION_USER:$APPLICATION_USER target/*.jar /app/app.jar
WORKDIR /app

USER $APPLICATION_USER

LABEL maintainer="@melihhtasci"
LABEL version="1.0"
LABEL description="Foreign Exchange Application"

ENTRYPOINT ["java", "-jar", "/app/app.jar"]