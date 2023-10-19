# Invext

# Central de Atendimento Invext

Este é um sistema de gerenciamento de central de atendimento da Invext que distribui solicitações para times de atendimento com base em regras específicas.

## Descrição do Problema

A Invext é uma fintech que estruturou sua central de relacionamento para atender diferentes tipos de solicitações dos clientes, incluindo "Problemas com cartão" e "Contratação de empréstimo". As solicitações são direcionadas para três times de atendimento: Cartões, Empréstimos e Outros Assuntos. Cada atendente pode atender no máximo 3 pessoas simultaneamente. Quando todos os atendentes de um time estão ocupados, as solicitações são enfileiradas e distribuídas assim que possível.

## Funcionalidades

- Criação de solicitações com base no tipo.
- Criação de atendentes e associação a um time de atendimento.
- Criação de times de atendimento.
- Enfileiramento e distribuição automática de solicitações.

## Controladores

### Atendimento Controller

O controlador `Atendimento` lida com operações relacionadas aos atendentes.

- `GET /atendentes/time/{timeId}`: Lista os atendentes por time de atendimento.
- `POST /atendentes`: Cria um novo atendente e o associa a um time de atendimento.

### Solicitacao Controller

O controlador `Solicitacao` lida com operações relacionadas às solicitações.

- `POST /solicitacoes`: Cria uma nova solicitação com base no tipo.

### TimeAtendimento Controller

O controlador `TimeAtendimento` lida com operações relacionadas aos times de atendimento.

- `POST /timeatendimentos`: Cria um novo time de atendimento.

## Serviços

### Atendimento Service

O serviço `AtendimentoService` contém a lógica de negócios relacionada aos atendentes e suas interações com as solicitações.

- `listarAtendentesPorTime(timeId)`: Lista os atendentes de um time de atendimento específico.
- `criarAtendente(atendente)`: Cria um novo atendente e o associa a um time de atendimento com base em regras específicas.
- `criarSolicitacao(solicitacao)`: Cria uma nova solicitação e a associa a um atendente disponível ou a uma fila de espera.

### FilaEspera Service

O serviço `FilaEsperaService` gerencia a fila de espera de solicitações quando todos os atendentes de um time estão ocupados.

- `enfileirarSolicitacao(solicitacao, timeAtendimento)`: Enfileira uma solicitação para um time de atendimento específico.
- `obterProximaSolicitacao(timeAtendimento)`: Obtém a próxima solicitação na fila de espera de um time de atendimento.

### TimeAtendimento Service

O serviço `TimeAtendimentoService` lida com operações relacionadas aos times de atendimento.

- `criarTimeAtendimento(timeAtendimento)`: Cria um novo time de atendimento.

## Requisitos de Instalação

Para executar este sistema, você precisará ter os seguintes requisitos instalados:

- **Java**: version "11.0.20.1" 2023-08-24 LTS.
- **Spring Boot**: Framework de desenvolvimento para Java.
- **Banco de Dados**: Configure um banco de dados compatível e ajuste as configurações no arquivo `application.properties`.

## Configuração

O sistema pode ser configurado para atender às suas necessidades. Ajuste as configurações do aplicativo, como porta e banco de dados, no arquivo `application.properties`.

## Testando a API com Postman

Você pode usar o Postman para testar os endpoints da API de forma fácil e conveniente. Abaixo estão exemplos de como testar as principais funcionalidades da API usando o Postman.

### Criação de Time de Atendimento

1. Abra o Postman e crie uma nova solicitação.

2. Configure a solicitação para o endpoint de criação de time de atendimento:

   - Método: `POST`
   - URL: `http://localhost:8080/timeatendimentos`

3. No corpo da solicitação, defina os detalhes do time de atendimento:

   ```json
   {
       "nome": "Cartões"
   }
