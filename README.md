# Projeto Kafka - Comunicação entre Microserviços

Este projeto é composto por **dois microserviços Java Spring Boot** que se comunicam entre si através do **Apache Kafka**.

---

## ✨ Visão Geral

- **pedido-service**: serviço responsável por receber requisições HTTP e publicar mensagens em um tópico Kafka (`novos-pedidos`).
- **notificacao-service**: serviço responsável por consumir mensagens do tópico Kafka e simular o envio de uma notificação.

A comunicação entre eles é **assíncrona** via Kafka.

---

## 🧱 Tecnologias Utilizadas

- Java 21 + Spring Boot 3
- Apache Kafka
- Docker + Docker Compose
- Lombok
- Spring for Apache Kafka
- Spring Web

---

## 🛠️ Como Executar Localmente

### 1. Clone os dois projetos em diretórios separados


git clone https://github.com/Samara-Bastos/Pedido_Service_Java.git
git clone https://github.com/Samara-Bastos/Notificacao_Service_Java.git

---

###  2. No projeto pedido-service, inicie o ambiente com Docker Compose

cd pedido-service
docker-compose -f compose.yaml up -d

### Isso irá subir:

- Kafka
- Zookeeper

- Obs: Não é necessário subir o compose no notificacao-service, pois Kafka já estará ativo.

---

### 3. Crie o tópico Kafka (caso ainda não tenha sido criado)

docker exec -it kafka kafka-topics --create --bootstrap-server localhost:9092 --replication-factor 1 --partitions 1 --topic novos-pedidos

- Dica: para verificar os tópicos criados:

docker exec -it kafka kafka-topics --bootstrap-server localhost:9092 --list

---

### 4. Rode os dois serviços em dois terminais separados:

Terminal 1: pedido-service
./gradlew bootRun

Terminal 2: notificacao-service
./gradlew bootRun

---

## 💡 Como Testar
Envie uma requisição POST para o pedido-service:

curl -X POST http://localhost:8080/pedidos \
-H "Content-Type: application/json" \
-d '{"nomeCliente": "Samara", "produto": "Livro"}'

- Verifique no terminal do notificacao-service que a mensagem foi consumida com sucesso e uma notificação foi "enviada".

---

## 🧠 O que foi aprendido sobre Kafka

- Kafka é uma plataforma de mensagens distribuída, usada para comunicação assíncrona entre serviços.

- Um Producer publica mensagens em um tópico.

- Um Consumer escuta esse tópico e processa as mensagens recebidas.

- Cada mensagem pode conter dados estruturados (como JSON), transmitidos entre microserviços sem acoplamento.

- A criação manual de tópicos permite controle sobre particionamento, replicação e testes.

- O docker-compose facilita a configuração local do Kafka para ambientes de desenvolvimento.

---

## ✅ Resumo das Funções Kafka Usadas

| Componente         | Função                                                                 |
|--------------------|------------------------------------------------------------------------|
| `KafkaTemplate`    | Utilizado no `pedido-service` para publicar mensagens no tópico        |
| `@KafkaListener`   | Utilizado no `notificacao-service` para consumir mensagens do tópico   |
| `ProducerFactory()`| Configura o produtor Kafka, definindo propriedades como servidor, serialização e etc |
