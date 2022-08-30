# using corretto 8 from aws public repo
FROM amazoncorretto:8u322-alpine3.13-jre

COPY target/rgil-api.jar rgil-api.jar

EXPOSE 8080
# ENV spring_profiles_active=prod
ENV TZ='Asia/Kolkata'
ENV JAVA_ENV=prod

ENTRYPOINT ["java","-jar","/rgil-api.jar","--spring.profiles.active=prod"]