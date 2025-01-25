FROM openjdk:17-jdk-slim
WORKDIR /app
COPY build/libs/stock-0.0.1-SNAPSHOT.jar /app/stock-0.0.1-SNAPSHOT.jar
CMD ["java", "-jar", "stock-0.0.1-SNAPSHOT.jar"]

FROM postgres:16.2
# Создание каталога для SQL-скриптов
RUN mkdir -p /docker-entrypoint-initdb.d/
COPY / /docker-entrypoint-initdb.d/
EXPOSE 5432