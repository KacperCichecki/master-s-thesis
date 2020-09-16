FROM adoptopenjdk/openjdk11:latest
RUN addgroup --system spring && adduser --system --ingroup spring spring
USER root
RUN mkdir /my-tomcat && chown spring:spring /my-tomcat
USER spring:spring
COPY target/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]