# Projeto com Spring Boot e banco de dados PostgreSQL.

- ### _Recursos utilizados no desenvolvimento:_ 
  - SpringBoot
  - PostgreSQL
  - Swagger/OpenAPI
  - JWT
  - Oauth0
  - Lombok
#

### 📄 **Documentação**

- Documentação da API com Swagger ([clique aqui](http://localhost:8080/swagger-ui/index.html)).

- Ou acesse: http://localhost:8080/swagger-ui/index.html

#

No PostgreSQL, criar a DataBase com o nome: desafio

#

- ### _Usuários para se autenticar:_
```
{
  "username": "admin",
  "password": "7777"
}
```
```
{
  "username": "root",
  "password": "7777"
}
```
```
{
  "username": "user",
  "password": "7777"
}
```
#

Usuario ***admin*** só tem a permissão de ***ADMIN***.

Usuario ***root*** tem a permissão de ADMIN e de ***CLIENT***.

Usuario ***user*** só tem a permissão de ***CLIENT***.
