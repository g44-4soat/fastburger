FROM openjdk:17-jdk-slim

WORKDIR /app

COPY target/fastburger-0.0.1-SNAPSHOT.jar /app/fastburger-0.0.1-SNAPSHOT.jar

# Exponha a porta em que a aplicação Spring está em execução (substitua a porta padrão se necessário)
EXPOSE 8080

# Comando para iniciar a aplicação quando o contêiner for iniciado
CMD ["java", "-jar", "fastburger-0.0.1-SNAPSHOT.jar"]

