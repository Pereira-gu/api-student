# Estágio 1: Build (Performance: Compila o código dentro de um container)
FROM maven:3.9.6-amazoncorretto-21 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Estágio 2: Runtime (Cloud Ready: Imagem final leve apenas com o JRE)
FROM amazoncorretto:21-alpine
WORKDIR /app
# Copia o .jar gerado no estágio anterior
COPY --from=build /app/target/*.jar app.jar

# Porta que o Spring Boot usa
EXPOSE 8080

# Comando para rodar a API
ENTRYPOINT ["java", "-jar", "app.jar"]