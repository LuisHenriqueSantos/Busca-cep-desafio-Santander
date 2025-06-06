#  BuscaCEP

Projeto desenvolvido como parte de um desafio técnico com o objetivo de aplicar conceitos de arquitetura limpa, SOLID, boas práticas de desenvolvimento em Java com Spring Boot, além da integração com API externa para busca de informações de CEP.

## 📌 Descrição do Desafio
1. Criar um desenho de solução explicando a aplicação.
2. Implementar uma aplicação capaz de realizar busca de CEP via API externa (preferencialmente utilizando mock, como Wiremock, Mockoon ou similares).
3. Registrar os logs das consultas em banco de dados, incluindo o horário da consulta e os dados retornados da API.
4. Aplicar princípios básicos de SOLID.
5. Disponibilizar o repositório publicamente no GitHub.

## 👨‍💻 Desenho de solução para o desafio

![Image](https://github.com/user-attachments/assets/35d8b76e-9310-4a7e-832b-8485baaa0beb)

## 🛠️ Tecnologias Utilizadas

- Java 21
- Maven
- Spring Boot
- WireMock (mock da API externa de CEP)
- MySQL (via Docker)
- Flyway
- Swagger
- Docker

## 📌 Funcionalidades

- ✅ Consultar CEPs via API externa mockada
- ✅ Gravar logs com data/hora no banco de dados
- ✅ Aplicar princípios SOLID e boas práticas
- ✅ Utilizar Docker + Banco + Mock + Spring Boot
- ✅ Documentar e expor o projeto publicamente

## 📊 Fluxo da Aplicação
- ✅ Cliente faz requisição: `/api/ceps/{cep}`
- ✅ Controller delega para Service
- ✅ Service chama API externa (Wiremock)
- ✅ Resposta é retornada ao cliente
- ✅ Log é salvo no banco com horário

## 📄 Estrutura do projeto
```
src/
└── main/
└── java/
└── com.desafiosantander.buscacep/
├── configs/ # Configurações gerais do projeto (Swagger, Beans)
├── controllers/ # Camada de Controllers
│ └── docs/ # Interface para documentação com Swagger
├── entities/ # Entidades do banco de dados
├── exceptions/ # Tratamento de exceções
│ └── handler/ # Manipuladores de exceções customizados
├── record/ # Records para dados imutáveis (como respostas de API)
├── repository/ # Interfaces de repositório JPA
├── service/ # Camada de serviços (regras de negócio)
└── BuscaCepApplication.java # Classe principal
```

---
## 🔗 Endpoints

| Método HTTP | Endpoint       | Descrição             |
|-------------|----------------|-----------------------| 
| `GET`       | `api/ceps/cep` | Consulta dados do CEP |

---

## 🧩 Princípios SOLID Aplicados
- **S** - Separação clara de responsabilidades entre controller, service e repository.
- **O** - Fácil extensão para novos tipos de busca de endereço (ex: por logradouro).
- **L** - Substituição de implementações simuladas da API (ex: WireMock) por reais sem alterar o contrato.
- **I** - Uso de interfaces como ICepController.
- **D** - Inversão de dependência através de injeção via Spring.

## ▶️ Como executar

### 1. Executar o container para o Wiremock
```bash
docker run -d --name wiremock -p 8081:8080 wiremock/wiremock
```

### 2. Fazer inserção dos stub mappings via Postman
```
POST http://localhost:8081/__admin/mappings
```

### 3. Exemplo de JSON (stub mappings) a ser inserido
```json
{
  "request": {
    "method": "GET",
    "url": "/cep/18953008"
  },
  "response": {
    "status": 200,
    "jsonBody": {
      "cep": "18953008",
      "rua": "Rua Teste 2, 500",
      "bairro": "Jd dos Brilhantes",
      "cidade": "Ipaussu",
      "uf": "SP"
    },
    "headers": {
      "Content-Type": "application/json"
    }
  }
}
```

### 4. Exemplo de retorno do stubs
```json
{
    "id": "e7eb7dde-90fb-4ad3-bc2f-67102c479ffd",
    "request": {
        "url": "/cep/18953008",
        "method": "GET"
    },
    "response": {
        "status": 200,
        "jsonBody": {
            "cep": "18953008",
            "rua": "Rua Teste 2, 500",
            "bairro": "Jd dos Brilhantes",
            "cidade": "Ipaussu",
            "uf": "SP"
        },
        "headers": {
            "Content-Type": "application/json"
        }
    },
    "uuid": "e7eb7dde-90fb-4ad3-bc2f-67102c479ffd"
}
```

### 5. Testando a aplicação via Postman
```
GET http://localhost:8080/api/ceps/cep
```

### 6. Testando via Swagger
```
http://localhost:8080/swagger-ui/index.html
```

### 7. Exemplo de Response
```json
{
    "cep": "18953008",
    "rua": "Rua Teste 2, 500",
    "bairro": "Jd dos Brilhantes",
    "cidade": "Ipaussu",
    "uf": "SP"
}
```

### 8. Execute a classe de inicialização
Execute a classe `BuscaCepApplication.java`

# 🌐 Acessando a API
Após subir a aplicação, acesse:
- Documentação: [Acesse a documentação aqui](http://localhost:8080/v3/api-docs)

🧑‍💻 Autor
Desenvolvido por **Luis Henrique Santos**
