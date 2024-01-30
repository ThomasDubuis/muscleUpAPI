FROM amazoncorretto:11-alpine-jdk
LABEL com.ynov.muscleup.authors="ThomasDbs"
WORKDIR /app
COPY ./build/libs/MuscleUp-0.0.1.jar /app
EXPOSE 8080
ENTRYPOINT ["java","-jar","/MuscleUp-0.0.1.jar"]