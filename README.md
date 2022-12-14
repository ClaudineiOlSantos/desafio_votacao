# Desafio Votacão

Aplicação criada para o desafio do sistema de votação

## Arquitetura

O sistema utiliza a linguagem java juntamente com framework spring boot. Por ser uma linguagem extremamente madura e
adotada pelo mercado. Utilizada por grandes empresas por todo mundo.

Estrutura de pastas:

- builder
- dto
- entity
- enumeration
- exception
- producer
- rabbit/config
- repository
- resource
- service

### Dependências

- JPA
- Web
- Postegre SQL
- Lombok
- Bean validation
- RabbitMQ
- maven

## Run

Para rodar o projeto adicionei um docker-compose na raiz do projeto para facilitar. Com o docker inicializado
rode ```docker-compose up``` a aplicação está no Docker Hub e deverá ser baixada para máquina e deverá subir 3
containers:

- PostgreSql
- RabbitMQ
- Voto (Aplicação)

OBS: Pode também subir o container localmente adicionando o arquivo docker-compose um nível acima e descomentando as linha
30,31 e 32 e então rode ```docker-compose up --build```

## Endpoints

### Pauta

***Persistir:***
```
curl --location --request POST 'http://localhost:8080/api/pauta' \
--header 'Content-Type: application/json' \
--data-raw '{
    "titulo":"teste 2",
    "descricao":"teste desc 2"
}'
```

***Listar:***

```
curl --location --request GET 'http://localhost:8080/api/pauta'
```

***Buscar por ID:***

```
curl --location --request GET 'http://localhost:8080/api/pauta/{id}'
```

### Associado

***Persistir:***
```
curl --location --request POST 'http://localhost:8080/api/associado' \
--header 'Content-Type: application/json' \
--data-raw '{
    "cpf":"17713560068",
    "nome":"fulano 7"
}'
```

***Listar:***

```
curl --location --request GET 'http://localhost:8080/api/associado'
```

***Buscar por ID:***

```
curl --location --request GET 'http://localhost:8080/api/associado/{id}'
```

### Sessão

***Persistir:***

```
curl --location --request POST 'http://localhost:8080/api/sessao' \
--header 'Content-Type: application/json' \
--data-raw '{
    "pautaId":"63c8a28b-858e-4165-b7b9-0b612a7b26a7",
    "inicio":"2022-09-06T12:00",
    "fim":"2022-09-06T12:05"
}'
```

***Listar:***

```
curl --location --request GET 'http://localhost:8080/api/sessao'
```
***Buscar por Id***

```
curl --location --request GET 'http://localhost:8080/api/sessao/{id}'
```

### Voto

***Votar:***

```
curl --location --request POST 'http://localhost:8080/api/voto' \
--header 'Content-Type: application/json' \
--data-raw '{
    "sessaoId":"cea17bde-6c2b-4bab-a331-a7186b4317cf",
    "cpf":"17713560068",
    "voto":"NAO"
}'
```

***Resultado (Poderia ficar no contexto da Sessão):***

```
curl --location --request GET 'http://localhost:8080/api/voto/resultado/{sessaoId}'
```