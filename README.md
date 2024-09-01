# Serviço de Pedidos - Microserviço com RabbitMQ e Postgres

Este projeto implementa um microserviço para gerenciar pedidos, desenvolvido em Java, utilizando RabbitMQ para mensageria e Postgres como banco de dados. A aplicação permite o consumo de mensagens de pedidos e gravação no banco de dados, além de disponibilizar endpoints para consulta via API.

## Tecnologias Utilizadas

- **Java 22**: Linguagem de programação.
- **Spring Boot**: Framework para desenvolvimento da aplicação.
- **Postgres**: Banco de dados relacional para armazenar os pedidos.
- **RabbitMQ**: Ferramenta de mensageria para processar pedidos de forma assíncrona.
- **Docker**: Plataforma para containerização dos serviços.
- **Docker Compose**: Orquestrador para gerenciar múltiplos contêineres.

## Pré-requisitos

Antes de rodar o projeto, certifique-se de ter as seguintes ferramentas instaladas em sua máquina:

- [Docker](https://www.docker.com/get-started)
- [Docker Compose](https://docs.docker.com/compose/install/)
- Conta no [Docker Hub](https://hub.docker.com/) (para puxar as imagens)

## Estrutura do Projeto

- **src/**: Código fonte da aplicação.
- **docker-compose.yml**: Arquivo de orquestração que configura os serviços Docker (Postgres, RabbitMQ e a aplicação Java).

## Como Rodar o Projeto

### 1. Clonar o Repositório

Clone este repositório na sua máquina local:

```bash
git clone https://github.com/seu-usuario/servico-de-pedidos.git
cd servico-de-pedidos
```
### 2. Compilar a Aplicação

Se for necessário compilar a aplicação, utilize o Maven:

```bash
mvn clean install
```

### 3. Build e Push da Imagem Docker

Crie a imagem Docker da aplicação:

```bash
docker build -t seu-usuario/servico-de-pedidos:1.0 .
```
Faça login no Docker Hub:

```bash
docker login
```
Suba a imagem para o Docker Hub:

```bash
docker push seu-usuario/servico-de-pedidos:1.0
```

### 4. Configurar e Rodar o Ambiente com Docker Compose

No diretório onde o arquivo docker-compose.yml está localizado, execute o seguinte comando:

```bash
docker-compose up -d
```
Isso irá iniciar os contêineres da aplicação, do RabbitMQ e do Postgres.

### 5. Acessar a Aplicação

Após rodar o docker-compose, você pode acessar a aplicação em:

API da Aplicação: http://localhost:8080

Painel do RabbitMQ: http://localhost:15672 (Usuário e senha estão definidos no docker-compose.yml como guest/guest)

### 6. Parar os Serviços
Quando terminar de usar os serviços, você pode parar e remover os contêineres com:
```bash
docker-compose down
```

## Documentação das APIs

A aplicação expõe alguns endpoints REST para gerenciar pedidos:

### 1. Consultar o Valor Total do Pedido

- Endpoint: /v1/pedidos/valor-total/{codigoPedido}
- Método HTTP: GET
- Descrição: Retorna o valor total de um pedido, dado o código do pedido.
- Exemplo de Requisição:

```bash
curl -X GET http://localhost:8080/v1/pedidos/valor-total/1001
```

- Exemplo de Resposta:
```json
210.0
```
### 2. Quantidade de Pedidos por Cliente

- Endpoint: /v1/relatorios/quantidade-pedidos
- Método HTTP: GET
- Descrição: Retorna uma lista com os clientes (ID e nome) e a quantidade de pedidos que cada um realizou.
- Exemplo de Requisição:

```bash
curl -X GET http://localhost:8080/v1/relatorios/quantidade-pedidos
```
- Exemplo de Resposta:
```json
{
  "clientes": [
    {
      "codigo": 1,
      "nome": "Maria",
      "quantidade_pedidos": 3
    },
    {
      "codigo": 2,
      "nome": "João",
      "quantidade_pedidos": 5
    }
  ]
}
```
### 3. Lista de Pedidos por Cliente

- Endpoint: /v1/pedidos/cliente/{codigoCliente}
- Método HTTP: GET
- Descrição: Retorna a lista de pedidos realizados por um cliente, dado o código do cliente.
- Exemplo de Requisição:
```bash
curl -X GET http://localhost:8080/v1/pedidos/cliente/1

```
- Exemplo de Resposta:
```json
[
   {
      "codigoPedido": 1001,
      "codigoCliente": 1,
      "itens": [
         {
            "produto": "lápis",
            "quantidade": 100,
            "preco": 1.1
         },
         {
            "produto": "caderno",
            "quantidade": 10,
            "preco": 10.0
         }
      ]
   }
]


```

# Comunicação via Mensagem com RabbitMQ
## Publicar uma Mensagem na Fila

Para enviar uma mensagem para a fila RabbitMQ que será consumida pela aplicação, siga as instruções:

1. Acessar o Painel do RabbitMQ:
   - URL: http://localhost:15672
   - Usuário: guest
   - Senha: guest
2. Publicar uma Mensagem:
   - Vá para a aba Queues, selecione a fila desejada, e vá até a seção Publish message.
   - No campo Payload, adicione o seguinte JSON de exemplo:

```json
{
  "codigoPedido": 1001,
  "codigoCliente": 1,
  "itens": [
    {
      "produto": "lápis",
      "quantidade": 100,
      "preco": 1.10
    },
    {
      "produto": "caderno",
      "quantidade": 10,
      "preco": 10.00
    }
  ]
}

```

3. Enviar a Mensagem: Clique em Publish message.

### Exemplo de Consumo de Mensagem
A aplicação Java consumirá essa mensagem automaticamente da fila, salvará o pedido no banco de dados Postgres, e disponibilizará as consultas através dos endpoints API descritos acima.

## Referências
- Documentação do Spring Boot: https://spring.io/projects/spring-boot
- Documentação do RabbitMQ: https://www.rabbitmq.com/
- Documentação do Docker: https://docs.docker.com/