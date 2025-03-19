# Fruit Management System

## Overview
This project is a Fruit Management System built with Java, Spring Boot, and Maven. It provides APIs for managing fruits, users, and authentication.

## Prerequisites
- Java 17 or higher
- Maven 3.6.0 or higher
- PostgreSQL (or any other database you are using)

## Setup

### Clone the Repository
```bash
git clone https://github.com/Thiagovb62/fruit-management-system.git
cd fruit-management-system
```

### Configure the Database
Update the `application.properties` file located in `src/main/resources` with your database configuration:
```
spring.datasource.url=jdbc:postgresql://localhost:5432/your_database
spring.datasource.username=your_username
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
```

### Build the Project
```bash
mvn clean install
```

### Roles
The system has two roles:

- `VENDEDOR` thiagovbfazendas@.com  e senha 123456
- `ADMIN`     thiagovbAdm@gmail.com e senha 123456

## API Endpoints

### Autorization
- **Login**
    - **URL:** `/user/register`
    - **Method:** POST
    - **Request Body:**
      ```json
      {
        "email": "user@example.com",
        "password": "password"
      }
      ```
    - **Response:**
      ```json
      {
        "status: HttpStatus.OK"
      }
      ```
      
### Authentication
- **Login**
    - **URL:** `/user/login`
    - **Method:** POST
    - **Request Body:**
      ```json
      {
        "email": "user@example.com",
        "password": "password"
      }
      ```
    - **Response:**
      ```json
      {
        "token": "jwt-token"
      }
      ```

### Fruits
- **Get All Fruits**
    - **URL:** `/furtas/getAll`
    - **Method:** GET
  - **Authorization:** `Bearer jwt-token`
  - **Role:** `VENDEDOR`
    - **Response:**
      ```json
      [
        {
          "id": 1,
          "nome": "Apple",
          "classificacao": 1,
          "valorVenda": 2.5,
          "qtdDisponivel": 100,
          "fresca": true
        }, 
        {
          "id": 2,
          "nome": "Banana",
          "classificacao": 2,
          "valorVenda": 1.5,
          "qtdDisponivel": 200,
          "fresca": true
        },
        {
          "etc..."
        }
      ]
      ```

- **Add a New Fruit**
- **URL:** `/frutas/add`
- **Method:** POST
- **Authorization:** `Bearer jwt-token`
- **Role:** `ADMIN`
- **Request Body:**
  ```json
  {
    "nome": "Banana",
    "classificacao": 2,
    "valorVenda": 1.5,
    "qtdDisponivel": 200,
    "fresca": true
  }
  ```
- **Response:**
  ```json
  {
    "status: HttpStatus.OK"
  }
  ```
**Get a Fruit by Name**
- **URL:** /frutas/findByName
- **Method:** POST
- **Authorization:** `Bearer jwt-token`
- **Role:** `VENDEDOR`
- **Request Body:**
```json
{
  "nome": "Banana"
}
```
- **Response:**
```json
{
    "id": 2,
    "nome": "Banana",
    "classificacao": 2,
    "valorVenda": 1.5,
    "qtdDisponivel": 200,
    "fresca": true
}

```
**List all fruits by classification**
- **URL:** `/frutas/findByClassification`
- **Authorization:** `Bearer jwt-token`
- **Role:** `VENDEDOR`
- **Method:** GET
- **Request Body:**
- EXTRA(1, "Extra"),
- DE_PRIMEIRA(2, "De Primeira"),
- DE_SEGUNDA(3, "De Segunda"),
- DE_TERCEIRA(4, "De Terceira");

```json
{
    "classificacao": 2
}
```
- **Response:**
```json
{
    "id": 2,
    "nome": "Banana",
    "classificacao": 2,
    "valorVenda": 1.5,
    "qtdDisponivel": 200,
    "fresca": true
}
```
**And Others types of List with another params,see the FrutaController to see All !**

### Venda
- **Register a sale**
    - **URL:** `/venda/add`
    - **Method:** POST
    - **Authorization:** `Bearer jwt-token`
    - **Role:** `VENDEDOR`
    - **Response:**
- **Request Body:**
```json
[
  {
    "frutaID": 3,
    "discount": 0.5,
    "qtdEscolhida": 10
  },
  {
    "frutaID": 5,
    "discount": 0.1,
    "qtdEscolhida": 15
  }
]
```
- **Response:**
```json

{
  "HTTPStatus": "OK",
}

```

### Historico De Venda
- **Get All Sales**
    - **URL:** `/historicoVenda/all`
    - **Authorization:** `Bearer jwt-token`
    - **Role:** `VENDEDOR`
    - **Method:** GET
      - **Response:**
        ```json
           [
              {
              "id": "93f6ed07-eb0a-44a6-92e8-833625b53582",
              "dataVenda": "2025-03-18T11:29:03.554046",
              "valorTotal": 2.5,
              "qtdEscolhida": 10,
              "frutaVendida": [
              {
                "id": 1,
                "nome": "banana",
                "classificacao": "EXTRA",
                "fresca": true,
                "qtdDisponivel": 70,
                "valorVenda": 2.5
                }
              ]
                },
           {
              "id": "93f6ed07-eb0a-44a6-92e8-833625b53582",
              "dataVenda": "2025-03-18T11:29:03.554046",
              "valorTotal": 2.5,
              "qtdEscolhida": 10,
              "frutaVendida": [
              {
                "id": 1,
                "nome": "banana",
                "classificacao": "EXTRA",
                "fresca": true,
                "qtdDisponivel": 70,
                "valorVenda": 2.5
                }
              ]
          }
        ]
```

## Error Handling
Global exception handling is implemented to ensure that errors are returned in a consistent format. Common exceptions include:
- `EntityNotFoundException`: Returns a 404 status with a message.
- `AuthenticationException`: Returns a 401 status with a message.
- `IllegalArgumentException`: Returns a 400 status with a message.
- `Exception`: Returns a 500 status with a message.

