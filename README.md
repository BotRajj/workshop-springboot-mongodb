# Projeto MongoDB com Spring Boot
[![NPM](https://img.shields.io/npm/l/react)](https://github.com/BotRajj/workshop-springboot-mongodb/blob/main/LICENSE) 

# Sobre o projeto

Ainda em andamento. Esse é uma projeto web services, com Spring Boot e MongoDB construído em um dos cursos do professor Dr.Nelio Alves DevSuperior.

O projeto consiste na implementação de algumas etapas Back End em um site com posts e comentários.

# Objetivo geral:
  - Compreender as principais diferenças entre paradigma orientado a documentos e relacional
  - Implementar operações de CRUD
  - Refletir sobre decisões de design para um banco de dados orientado a documentos
  - Implementar associações entre objetos
    - Objetos aninhados
    - Referências
  - Realizar consultas com Spring Data e MongoRepository

## Modelo conceitual
![Modelo Conceitual](https://github.com/BotRajj/assets/blob/main/wk-sb-mg-nelio/design%20modelo%20de%20domino%20wksbmg%202.jpeg)
![design modelo de domino](https://github.com/BotRajj/assets/blob/main/wk-sb-mg-nelio/design%20modelo%20de%20domino%20wksbmg.jpeg)
![design modelo de domino](https://github.com/BotRajj/assets/blob/main/wk-sb-mg-nelio/modelo%20conceitual%20wksbmg.jpeg)
![Instancia de dominio](https://github.com/BotRajj/assets/blob/main/JSBJH/CamadasL%C3%B3gicas.jpeg)

# Tecnologias Back end utilizadas
- Java
- Spring Boot
- Maven
- MongoDB community server
- MongoDB Compass

# Detalhes do código

Na criação de entidades e recursos, segui os seguintes passos:
  - Atributos básicos, Ex.: Id, Nome.
  - Associações (iniciando as coleções)
  - Construtores (não incluindo coleções no construtor com parâmetros)
  - Getters e setters
  - hashCode e equals (implementação padrão: somente id)
  - Serializable (padrão: 1L)

No arquivo Instantiation na pa sta Config, está o povoamento do banco de dados e a instanciação da base de dados.

Para a camada de repositórios apenas extendi o org.springframework.data.mongodb.repository.MongoRepository e utilizei a anotação org.springframework.stereotype.Repository para cada serviço.

Quem acessará o repositório e manter a lógica de negócio é a camada de serviço, que por sua vez é chamada na camada de recursos. Os recursos recebem a requisição, enviam pra camada de serviço que processa ou envia para o repositório, que então o resultado é retornado para a camada de recursos e enviada de volta para a aplicação.

# Como executar o projeto

## Pré-requisitos
- Java 17
- MongoDb compass
- MongoDB community server
- Postman

```bash
# clonar repositório
git clone git@github.com:BotRajj/workshop-springboot-mongodb.git
```

Com o banco de dados MongoDB Compass, os dados persistem após a interrupção da aplicação. Antes da execução, crie um database no MongoDB com o nome "cursenelio_workshop_mongo".

```bash
spring.data.mongodb.uri=mongodb://localhost:27017/cursenelio_workshop_mongo
```

# End points:   

Utilizando o Postman, seguimos para as requisições.

Esse projeto já está implementado no Heroku, sendo possível fazer as requisições com o caminho:
- https://coure-javasb3-e46136a3d1e0.herokuapp.com

Exemplo:  https://coure-javasb3-e46136a3d1e0.herokuapp.com/endpoint

Ou utilize o caminho da sua implementação, caso já tenha.

E para execução local, os profile test e dev utiliza o caminho padrão:
- http://localhost:8080

Exemplo:  http://localhost:8080/endpoint

## Retornando todos os recursos.
### /users, /product, /order, /category

Enviando com o método GET, essa requisição retorna todos os recurso de cada.

## Retornando um recurso específico
### /users/id, /product/id, /order/id, category/id

Enviando com o método GET, essa requisição retorna apenas um recurso espeficicado. 

No caso do recurso não encontrado, o tratamento de exceções irá retorna 404 - Not found, a mensagem "Resource not found. Id + id" e um objeto de exceção manualmente tratado.

Classes envolvidas no tratamento de exceções
- NEW CLASS: services.exceptions.ResourceNotFoundException
- NEW CLASS: resources.exceptions.StandardError
- NEW CLASS: resources.exceptions.ResourceExceptionHandler
- ClasseServiceDoRecurso

## Criando um recurso
###/Users

Para inserção de usuários, com o método POST e no Body, subaba raw, formato JSON, enviamos os seguintes campos para inserção.  Nessa requisição o esperado é o 201 Created, indicando que foi criado com sucesso. 
```bash
{
"name": "Nome",
"email": "Email@email.com",
"phone": "1234-1234",
"password": "Senha"
}
```

Será implementada a criação dos demais recursos.

## Deletando um recurso
###/Users/Id

Para deletação de usuários, com o método DELETE, essa requisição irá deletar o usuário espeficiado. Nessa requisição o esperado são os retornos:
- 204 No Content, indicando que foi possível a deleção do usuário.
- 400 Bad Request, não sendo possível a deleção por conta da integridade referencial ou outro problema.

No caso do recurso não encontrado, o tratamento de exceções irá retorna 404 - Not found e um objeto de exceção manualmente tratado.

Classes envolvidas
- NEW CLASS: services.exceptions.DatabaseException
- ResourceExceptionHandler
- UserService
  - EmptyResultDataAccessException
  - DataIntegrityViolationException
  
Será implementada a deleção dos demais recursos.

## Atualizando um recurso
/Users
Para atualização de usuários, com o método PUT e no Body, subaba raw, formato JSON, enviamos os seguintes campos para atualização. Nessa requisição o esperado é o 200 OK, onde foi possível a atualização do usuário.

```bash
{
"name": "Nome",
"email": "Email@email.com",
"phone": "1234-1234"
}
```
No caso do recurso não encontrado, o tratamento de exceções irá retorna 404 - Not found e um objeto de exceção manualmente tratado. 

Classes envolvidas
- UserService
- UserResource
  
Será implementada a atualização dos demais recursos.


# Autor

Vitor Silva Machado

https://www.linkedin.com/in/vitor-silva-0a23302a1/ 
