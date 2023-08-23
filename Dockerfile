FROM openjdk:17
EXPOSE 8080
COPY ./build/libs/todolist-app.jar todolist-app.jar
ENTRYPOINT ["java", "-jar", "/todolist-app.jar"]