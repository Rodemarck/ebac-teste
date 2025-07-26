# EBAC Tasker API

Esta API foi desenvolvida para gerenciamento de tarefas com autenticação JWT, seguindo boas práticas de arquitetura em Java com Spring Boot.

## Arquitetura Utilizada

### 1. **Backend**
- **Framework:** Spring Boot 3.x
- **Linguagem:** Java 17
- **ORM:** Spring Data JPA (Hibernate)
- **Validação:** Bean Validation (Jakarta Validation)
- **Segurança:** Spring Security + OAuth2 Resource Server
- **Autenticação:** JWT (JSON Web Token) assinado com chave RSA
- **Banco de Dados:** MySQL (produção) e H2 (testes)
- **Testes:** JUnit 5, Mockito, RestAssured

### 2. **Estrutura de Pastas**
- `src/main/java/ebac/rode/teste/`
  - **controller/**: Endpoints REST (ex: [`controller.TaskController`](src/main/java/ebac/rode/teste/controller/TaskController.java), [`controller.AuthContoller`](src/main/java/ebac/rode/teste/controller/AuthContoller.java))
  - **entities/**: Modelos de domínio (ex: [`entities.User`](src/main/java/ebac/rode/teste/entities/User.java), [`entities.Task`](src/main/java/ebac/rode/teste/entities/Task.java), [`entities.Role`](src/main/java/ebac/rode/teste/entities/Role.java))
  - **repositories/**: Interfaces de acesso a dados (ex: [`repositories.UserRepository`](src/main/java/ebac/rode/teste/repositories/UserRepository.java), [`repositories.TaskRepository`](src/main/java/ebac/rode/teste/repositories/TaskRepository.java))
  - **services/**: Regras de negócio e integração (ex: [`services.AuthService`](src/main/java/ebac/rode/teste/services/AuthService.java), [`services.TaskService`](src/main/java/ebac/rode/teste/services/TaskService.java), [`services.JwtService`](src/main/java/ebac/rode/teste/services/JwtService.java))
  - **security/**: Implementações de autenticação e filtros JWT (ex: [`security.JwtTokenFilter`](src/main/java/ebac/rode/teste/security/JwtTokenFilter.java), [`security.UserDetailsImpl`](src/main/java/ebac/rode/teste/security/UserDetailsImpl.java))
  - **exceptions/**: Exceções customizadas (ex: [`exceptions.UserNotFoundException`](src/main/java/ebac/rode/teste/exceptions/UserNotFoundException.java), [`exceptions.TaskNotFoundException`](src/main/java/ebac/rode/teste/exceptions/TaskNotFoundException.java))
  - **config/**: Configurações de segurança (ex: [`config.SecurityConfig`](src/main/java/ebac/rode/teste/config/SecurityConfig.java))

- `src/main/resources/`
  - **application.properties**: Configuração do ambiente de produção
  - **application-test.properties**: Configuração do ambiente de testes (H2)
  - **schema.sql** e **data.sql**: Scripts de criação e carga inicial do banco
  - **app.key** e **app.pub**: Chaves RSA para assinatura/verificação do JWT

### 3. **Fluxo de Autenticação**
- Usuário registra e faz login via endpoints `/auth/register` e `/auth/login`.
- O login retorna um JWT assinado, que deve ser enviado no header `Authorization: Bearer <token>` para acessar endpoints protegidos.
- O filtro [`security.JwtTokenFilter`](src/main/java/ebac/rode/teste/security/JwtTokenFilter.java) valida o token em cada requisição.

### 4. **Gerenciamento de Tarefas**
- Endpoints RESTful em [`controller.TaskController`](src/main/java/ebac/rode/teste/controller/TaskController.java) para CRUD de tarefas.
- Cada tarefa está associada a um usuário autenticado.
- Validações de dados são feitas via Bean Validation.

### 5. **Tratamento de Erros**
- Exceções customizadas são tratadas globalmente em [`controller.ErroController`](src/main/java/ebac/rode/teste/controller/ErroController.java), retornando respostas padronizadas.

### 6. **Testes**
- Testes unitários e de integração em `src/test/java/ebac/rode/teste/` usando JUnit, Mockito e RestAssured.

### 7. **Deploy**
- **Docker:** Imagem multi-stage para build e execução.
- **docker-compose:** Serviço para banco MySQL e aplicação Java.

---

## Como rodar

1. **Build:**  
   ```sh
   docker compose up
```