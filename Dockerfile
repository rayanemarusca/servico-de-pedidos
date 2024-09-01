FROM openjdk:17-jdk-slim
WORKDIR /app
COPY target/servico-de-pedidos-0.0.1-SNAPSHOT.jar app.jar

# Copia o script de espera
COPY wait-for-30-seconds.sh /wait-for-30-seconds.sh
RUN chmod +x /wait-for-30-seconds.sh

# Copia o script de inicialização
COPY start.sh /start.sh
RUN chmod +x /start.sh

# Define o comando de inicialização
CMD ["/start.sh"]

EXPOSE 8080