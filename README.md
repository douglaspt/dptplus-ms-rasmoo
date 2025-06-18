## Descrição do Projeto

Este projeto implementa uma arquitetura baseada em microsserviços do curso RASMOO, com foco em autenticação, gerenciamento de usuários e envio de mensagens (como recuperação de senha). Os serviços se comunicam de forma assíncrona usando RabbitMQ, proporcionando escalabilidade, desacoplamento e facilidade de manutenção.

## Detalhamento dos Serviços

### 1. dptplus-customer-data-api

Responsável pelo gerenciamento de dados dos usuários e processos de autenticação.

- Realiza autenticação e autorização dos usuários, integrando-se ao Keycloak para gestão de identidade e permissões.
- Gerencia dados de cadastro dos clientes.
- Gera e armazena códigos de recuperação de senha.
- Publica eventos (como solicitações de recuperação de senha) no RabbitMQ para que outros serviços possam reagir, como o envio de e-mails.

### 2. dptplus-message-api

Responsável por tratar eventos de mensagens, principalmente o envio de e-mails, de forma desacoplada dos demais serviços.

- Consome eventos do RabbitMQ (por exemplo, pedidos de recuperação de senha publicados pelo customer-data-api).
- Gera e envia e-mails personalizados, utilizando o FreeMarker para templates dinâmicos.
- Integra-se com serviços de e-mail (JavaMailSender) para entrega das mensagens.
- Utiliza Dead Letter Queues (DLQ) para garantir robustez no processamento e evitar perda de mensagens em caso de falhas.

### 3. Integração com Keycloak

Keycloak atua como provedor de identidade e autorização.

- Centraliza autenticação dos usuários.
- Gerencia tokens de acesso e refresh.
- Controla permissões e papéis dos usuários em todos os microsserviços.

### 4. RabbitMQ Broker

Middleware de mensageria para comunicação assíncrona entre microsserviços.

- Recebe, roteia e entrega mensagens entre os diferentes microsserviços, desacoplando os fluxos de execução.
- Suporta padrões de troca de mensagens como Fanout Exchange e Dead Letter Exchange para maior robustez.

## Tecnologias Utilizadas

- **Java** — Linguagem principal do backend.
- **Spring Boot** — Framework para construção de microsserviços.
- **Spring Data JPA** — Abstração para persistência de dados.
- **Spring AMQP** — Integração com RabbitMQ.
- **RabbitMQ** — Message broker para comunicação assíncrona entre microsserviços.
- **Keycloak** — Gerenciamento de autenticação e autorização (Identity Provider).
- **Swagger/OpenAPI** — Documentação automática das APIs REST.
- **Lombok** — Redução de boilerplate no código Java.
- **FreeMarker** — Motor de templates para geração de e-mails.
- **Java Mail (Spring JavaMailSender)** — Envio de e-mails.
