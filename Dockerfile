# Etapa 1: Construção com Gradle
FROM gradle:8.14.3-jdk17 AS build

# Definir o diretório de trabalho no contêiner
WORKDIR /app

# Copiar o código fonte para o contêiner (diretório atual para /app no contêiner)
COPY . .

# Compilar o projeto (comando para pular os testes)
RUN gradle clean build -x test

# Etapa 2: Imagem final com OpenJDK (para executar o app)
FROM openjdk:17-jdk-slim

# Definir o diretório de trabalho no contêiner
WORKDIR /app

# Copiar o JAR gerado pela etapa anterior para o contêiner (de /app/build/libs para /app no contêiner)
COPY --from=build /app/build/libs/teste-0.0.1-SNAPSHOT.jar /app.jar

# Expor a porta do app (8080)
EXPOSE 8080

# Comando para rodar o Spring Boot no contêiner
CMD ["java", "-jar", "/app.jar"]