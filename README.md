# 🐾 Petshop API

Sistema de gerenciamento de petshop com backend em Kotlin + Spring Boot.

## 📌 Informações do Projeto

- **Nome do projeto:** Petshop API
- **Repositório:** https://github.com/cecilinda303/petshop
- **Integrantes:** Maria Cecilia do Prado (sozinha)
- **Entidades sob responsabilidade:** Pet e Tutor

## 📖 Sobre a Aplicação

A Petshop API é um backend REST que permite o gerenciamento de pets e seus tutores. 
O sistema resolve o problema de controle manual de cadastros em petshops, oferecendo:

- Cadastro, listagem, atualização e remoção de pets
- Cadastro, listagem, atualização e remoção de tutores
- Busca parcial por nome (busca inteligente que ignora maiúsculas/minúsculas)
- Validação automática de dados de entrada

## 🛠️ Tecnologias Utilizadas

- **Kotlin** — linguagem principal
- **Spring Boot 3.x** — framework backend
- **Spring Web** — APIs REST
- **Spring Data JPA** — persistência
- **Spring Security** — segurança
- **Bean Validation** — validação de dados
- **H2 Database** — banco de dados em memória
- **Gradle** — gerenciamento de dependências

## 🏗️ Camadas da Arquitetura

### Backend (`src/main/kotlin/com/petshop/`)

| Camada | Pasta | Descrição |
|---|---|---|
| Entidades | `entity/` | Classes JPA (Pet, Tutor) |
| Repositórios | `repository/` | Interfaces JpaRepository com queries JPQL |
| Serviços | `service/` | Regras de negócio e CRUD |
| Controllers | `controller/` | Endpoints REST |
| DTOs | `dto/` | Objetos de transferência (Request/Response) |
| Mappers | `mapper/` | Conversão entre Entity ↔ DTO |
| Exceções | `exception/` | Handler global de erros |
| Configuração | `config/` | Configurações do Spring |

## 🚀 Instruções de Instalação e Execução

### Pré-requisitos
- Java 21+
- Gradle (ou use o wrapper `./gradlew`)

### Executar localmente

```bash
# Clonar o repositório
git clone https://github.com/cecilinda303/petshop.git
cd petshop

# Rodar a aplicação
./gradlew bootRun
```

A aplicação sobe em: **http://localhost:8080**

### Console do H2 (visualizar banco)
- URL: http://localhost:8080/h2-console
- JDBC URL: `jdbc:h2:mem:petshopdb`
- Usuário: `sa`
- Senha: (vazia)

## 🔌 Endpoints Disponíveis

### Pets (`/api/pets`)

| Método | Rota | Descrição |
|---|---|---|
| GET | `/api/pets` | Lista todos os pets |
| GET | `/api/pets/{id}` | Busca pet por ID |
| GET | `/api/pets/buscar?nome=X` | Busca parcial por nome |
| POST | `/api/pets` | Cria novo pet |
| PUT | `/api/pets/{id}` | Atualiza pet |
| DELETE | `/api/pets/{id}` | Remove pet |

### Tutores (`/api/tutores`)

| Método | Rota | Descrição |
|---|---|---|
| GET | `/api/tutores` | Lista todos os tutores |
| GET | `/api/tutores/{id}` | Busca tutor por ID |
| GET | `/api/tutores/buscar?nome=X` | Busca parcial por nome |
| POST | `/api/tutores` | Cria novo tutor |
| PUT | `/api/tutores/{id}` | Atualiza tutor |
| DELETE | `/api/tutores/{id}` | Remove tutor |

## 📋 Exemplos de Uso

### Criar um Pet

```bash
curl -X POST http://localhost:8080/api/pets \
  -H "Content-Type: application/json" \
  -d '{"nome":"Rex","especie":"Cachorro","raca":"Labrador","peso":25.5,"dataNascimento":"2020-05-10","vacinado":true}'
```

### Buscar Pet por nome (busca parcial)

```bash
curl "http://localhost:8080/api/pets/buscar?nome=rex"
```