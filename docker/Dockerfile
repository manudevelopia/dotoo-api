FROM amazoncorretto:21-alpine-jdk

WORKDIR "/app"

COPY "app/build/libs/*.jar" "/app/"

EXPOSE 5050

ENTRYPOINT ["java","-cp","app.jar:*","info.developia.Launcher"]