# Desafio Cidades

## Tecnologias utilizada:

### Frontend:

- Node 22
- Angular 17
- Componentes PrimeNG

### Backend:

- Java 21
- Spring boot 3
- Banco de dados h2

# Como executar

## Backend

1. Instale o [Maven](https://maven.apache.org/download.cgi) ou qualquer outro programa que possa executar o spring boot;

2. Navegue para a pasta `backend-spring-boot`, usando o comando:

```
    cd backend-spring-boot/
```

3. Baixe as dependecias com o comando:

```
    mvn clean install
```

4. Execute o backend com o comando:

```
    mvn spring-boot:run
```

5. Servidor rodando no `http://localhost:8080`.

## Frontend (EM PRODUÇÃO)

1. tenha o node instalado na sua máquina

2. navegue para a pasta `frontend-angular`, usando o comando:

```
    cd frontend-angular
```

3. instale as dependencias com o comando:

```
    npm install
```

4. Execute o frontend, com o comando:

```
    npm start
```

## Após subir o backend o banco H2 pode ser acessado no seguinte endereço:

http://localhost:8080/placeti/h2-console

## Use os seguintes dados para conexão no H2:

- url: jdbc:h2:mem:database
- usuario: admin
- senha: admin
