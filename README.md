# Gym Management API

API REST para gerenciamento de academia desenvolvida com Java e Spring Boot, focada no estudo de JPA, relacionamentos entre entidades e boas práticas com Spring.

## Tecnologias

| Tecnologia | Versão |
|---|---|
| Java | 25 |
| Spring Boot | 4.0.6 |
| Spring Data JPA | - |
| SpringDoc OpenAPI (Swagger) | 3.0.2 |
| PostgreSQL | - |
| Lombok | - |
| Maven | - |

## Funcionalidades

- Cadastro e remoção de alunos
- Cadastro de avaliações físicas com listagem paginada
- Criação de treinos vinculados a alunos
- Cadastro e busca de exercícios por grupo muscular
- Relacionamentos JPA: `OneToOne`, `OneToMany`, `ManyToMany`
- Tratamento global de exceções com `@RestControllerAdvice`
- Documentação interativa via Swagger UI

## Endpoints

### Alunos — `/v1/alunos`
| Método | Rota | Descrição |
|---|---|---|
| POST | `/v1/alunos` | Cadastra um novo aluno |
| GET | `/v1/alunos/{id}/avaliacao` | Retorna a avaliação física do aluno |
| DELETE | `/v1/alunos/{id}` | Remove um aluno |

### Avaliações Físicas — `/v1/avaliacoes`
| Método | Rota | Descrição |
|---|---|---|
| POST | `/v1/avaliacoes` | Cadastra uma avaliação física |
| GET | `/v1/avaliacoes` | Lista todas as avaliações |
| GET | `/v1/avaliacoes/page/{page}/size/{size}` | Lista avaliações com paginação |

### Treinos — `/v1/treinos`
| Método | Rota | Descrição |
|---|---|---|
| POST | `/v1/treinos` | Cria um treino vinculado a um aluno |

### Exercícios — `/v1/exercicios`
| Método | Rota | Descrição |
|---|---|---|
| GET | `/v1/exercicios` | Lista todos os exercícios |
| POST | `/v1/exercicios` | Cadastra um exercício |
| GET | `/v1/exercicios/grupos/{grupoMuscular}` | Busca exercícios por grupo muscular |

## Relacionamentos entre Entidades

```
Aluno ──(1:1)──> AvaliacaoFisica
Aluno ──(1:N)──> Treino
Treino ──(N:N)──> Exercicio  (tabela: treinos_exercicios)
```

## Arquitetura

```
src/main/java/com/example/spring_boot_essentials/
├── controller/     # Camada HTTP (REST)
├── service/        # Regras de negócio
├── repository/     # Acesso a dados (Spring Data JPA)
├── model/          # Entidades JPA
├── dto/            # Objetos de transferência de dados
├── exception/      # Exceções customizadas
└── handler/        # GlobalExceptionHandler
```

## Configuração do Banco de Dados

O projeto utiliza PostgreSQL. Crie o banco antes de iniciar:

```sql
CREATE DATABASE gym;
```

As credenciais padrão estão em `src/main/resources/application.yaml`:

```yaml
datasource:
  url: jdbc:postgresql://localhost:5432/gym
  username: postgres
  password: admin
```

O Hibernate está configurado com `ddl-auto: update` — as tabelas são criadas/atualizadas automaticamente.

## Como executar

```bash
git clone https://github.com/Gustavo-Vinicius/spring-boot-essentials-jpa.git
cd spring-boot-essentials-jpa
./mvnw spring-boot:run
```

A aplicação sobe na porta **8081**.

## Documentação (Swagger UI)

Com a aplicação rodando, acesse:

```
http://localhost:8081/swagger-ui.html
```

## Autor

Gustavo Vinicius
