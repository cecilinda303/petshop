# 🐾 Petshop API — Backend

Backend da aplicação Petshop, desenvolvido em **Kotlin + Spring Boot** com autenticação JWT.

---

## 📌 Informações do Projeto

- **Nome do projeto:** Petshop API
- **Repositório:** https://github.com/cecilinda303/petshop
- **Integrantes:** Maria Cecilia do Prado 
- **Entidades sob responsabilidade:** Pet e Tutor

---

## 📖 Descrição da Aplicação

A **Petshop API** é um backend REST que gerencia o cadastro de **pets** e seus **tutores**. 
O sistema resolve o problema de controle manual de cadastros em petshops, oferecendo:

- Cadastro, listagem, atualização e remoção de pets
- Cadastro, listagem, atualização e remoção de tutores
- Busca parcial por nome (LIKE, ignorando maiúsculas/minúsculas)
- Validação automática de dados de entrada
- Autenticação de usuários com **JWT (JSON Web Token)**
- Senhas criptografadas com **BCrypt**

---

## 🛠️ Tecnologias Utilizadas

- **Kotlin** — linguagem principal
- **Spring Boot 3.x** — framework backend
- **Spring Web** — APIs REST
- **Spring Data JPA** — persistência de dados
- **Spring Security** — autenticação e autorização
- **JWT (jjwt)** — geração e validação de tokens
- **Bean Validation** — validação de dados
- **H2 Database** — banco de dados em memória
- **Gradle** — gerenciamento de dependências e build

---

## 🏗️ Camadas da Arquitetura

Estrutura de pastas em `src/main/kotlin/com/petshop/`:

| Camada | Pasta | Descrição |
|---|---|---|
| Entidades | `entity/` | Classes JPA (Pet, Tutor, Usuario) |
| Repositórios | `repository/` | Interfaces `JpaRepository` com queries JPQL |
| Serviços | `service/` | Regras de negócio e CRUD |
| Controllers | `controller/` | Endpoints REST |
| DTOs | `dto/` | Objetos de transferência (Request/Response) |
| Mappers | `mapper/` | Conversão entre Entity ↔ DTO |
| Segurança | `security/` | JwtUtil e JwtAuthFilter |
| Configuração | `config/` | SecurityConfig e CorsConfig |
| Exceções | `exception/` | Handler global de erros |

---

## 🚀 Instalação e Execução

### Pré-requisitos
- **Java 17+** (recomendado Java 21)
- Não é necessário ter Gradle instalado — o wrapper (`gradlew`) cuida disso

### Passo a passo

```bash
# Clonar o repositório
git clone https://github.com/cecilinda303/petshop.git
cd petshop

# (Opcional) Limpar builds antigos
./gradlew clean

# Rodar a aplicação
./gradlew bootRun
```

A aplicação sobe em: **http://localhost:8080**

### Console do H2 (visualizar o banco)

- **URL:** http://localhost:8080/h2-console
- **JDBC URL:** `jdbc:h2:mem:petshopdb`
- **Usuário:** `sa`
- **Senha:** (vazia)

---

## 🔐 Autenticação

A API usa **JWT**. As rotas `/auth/signup` e `/auth/signin` são **públicas**.
Todas as demais rotas (`/api/**`) exigem o **token JWT** no cabeçalho `Authorization`.

### Criar conta (signup)

```bash
curl -X POST http://localhost:8080/auth/signup \
  -H "Content-Type: application/json" \
  -d '{"email":"usuario@teste.com","senha":"123456"}'
```

Resposta:
```json
{"token":"eyJhbGciOiJIUzM4NCJ9...","tipo":"Bearer"}
```

### Fazer login (signin)

```bash
curl -X POST http://localhost:8080/auth/signin \
  -H "Content-Type: application/json" \
  -d '{"email":"usuario@teste.com","senha":"123456"}'
```

### Usar o token nas requisições

```bash
curl http://localhost:8080/api/pets \
  -H "Authorization: Bearer SEU_TOKEN_AQUI"
```

---

## 🔌 Endpoints

### Autenticação (`/auth`)

| Método | Rota | Descrição | Público? |
|---|---|---|---|
| POST | `/auth/signup` | Cria novo usuário | ✅ |
| POST | `/auth/signin` | Autentica e retorna token | ✅ |

### Pets (`/api/pets`) — *requer token*

| Método | Rota | Descrição |
|---|---|---|
| GET | `/api/pets` | Lista todos os pets |
| GET | `/api/pets/{id}` | Busca pet por ID |
| GET | `/api/pets/buscar?nome=X` | Busca parcial por nome |
| POST | `/api/pets` | Cria novo pet |
| PUT | `/api/pets/{id}` | Atualiza pet |
| DELETE | `/api/pets/{id}` | Remove pet |

### Tutores (`/api/tutores`) — *requer token*

| Método | Rota | Descrição |
|---|---|---|
| GET | `/api/tutores` | Lista todos os tutores |
| GET | `/api/tutores/{id}` | Busca tutor por ID |
| GET | `/api/tutores/buscar?nome=X` | Busca parcial por nome |
| POST | `/api/tutores` | Cria novo tutor |
| PUT | `/api/tutores/{id}` | Atualiza tutor |
| DELETE | `/api/tutores/{id}` | Remove tutor |

---

## 📋 Exemplos de Uso

### Criar um Pet (com token)

```bash
curl -X POST http://localhost:8080/api/pets \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer SEU_TOKEN_AQUI" \
  -d '{
    "nome":"Rex",
    "especie":"Cachorro",
    "raca":"Labrador",
    "peso":25.5,
    "dataNascimento":"2020-05-10",
    "vacinado":true
  }'
```

### Criar um Tutor (com token)

```bash
curl -X POST http://localhost:8080/api/tutores \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer SEU_TOKEN_AQUI" \
  -d '{
    "nome":"Maria Silva",
    "cpf":"12345678900",
    "email":"maria@email.com",
    "telefone":"11999999999",
    "dataCadastro":"2024-01-15",
    "ativo":true
  }'
```

### Buscar Pet por nome (busca parcial)

```bash
curl "http://localhost:8080/api/pets/buscar?nome=rex" \
  -H "Authorization: Bearer SEU_TOKEN_AQUI"
```

---

## ✅ Validações Implementadas

Os DTOs de Request usam **Bean Validation**:

**PetRequest:**
- `nome` — obrigatório, entre 2 e 100 caracteres
- `especie` — obrigatório, no máximo 50 caracteres
- `raca` — obrigatório
- `peso` — obrigatório, maior que zero
- `dataNascimento` — obrigatória, não pode ser futura
- `vacinado` — obrigatório

**TutorRequest:**
- `nome` — obrigatório, entre 2 e 100 caracteres
- `cpf` — obrigatório, 11 dígitos numéricos
- `email` — obrigatório, formato válido
- `telefone` — obrigatório, entre 10 e 20 caracteres
- `dataCadastro` — obrigatória, não pode ser futura
- `ativo` — obrigatório

---

## 🔒 Segurança Implementada

- **PasswordEncoder (BCrypt)** — senhas criptografadas
- **JwtUtil** — geração e validação de tokens JWT
- **JwtAuthFilter** — intercepta requisições e valida o token
- **SecurityFilterChain** — regras de autorização
- **Rotas públicas:** `/auth/signin`, `/auth/signup`, `/h2-console/**`
- **Demais rotas:** exigem autenticação via JWT
- **CORS** — configurado para permitir acesso do frontend

---

## 📁 Estrutura Resumida

```
src/main/kotlin/com/petshop/
├── PetshopApplication.kt
├── config/
│   ├── SecurityConfig.kt
│   └── CorsConfig.kt
├── controller/
│   ├── AuthController.kt
│   ├── PetController.kt
│   └── TutorController.kt
├── dto/
│   ├── AuthRequest.kt / AuthResponse.kt
│   ├── PetRequest.kt / PetResponse.kt
│   └── TutorRequest.kt / TutorResponse.kt
├── entity/
│   ├── Usuario.kt
│   ├── Pet.kt
│   └── Tutor.kt
├── exception/
│   └── GlobalExceptionHandler.kt
├── mapper/
│   ├── PetMapper.kt
│   └── TutorMapper.kt
├── repository/
│   ├── UsuarioRepository.kt
│   ├── PetRepository.kt
│   └── TutorRepository.kt
├── security/
│   ├── JwtUtil.kt
│   └── JwtAuthFilter.kt
└── service/
    ├── AuthService.kt
    ├── PetService.kt
    └── TutorService.kt
```

---

## 👨‍🏫 Contato

- **Aluna:** Maria Cecilia do Prado
- **Repositório:** https://github.com/cecilinda303/petshop
- **Professor:** antoniorcn@hotmail.com