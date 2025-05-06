## [APP-management-tool]

---

## Introduction

This project is part of a broader **Customer Management Tool**. We provide all necessary basic CRUD operations to handle user entities and another custom funcionalities. It is built using **Java 21 with Spring Boot**.

The architecture follows a clean **Hexagonal Architecture** structure, supports **RESTful HTTP** and operations are integrated through Reactive programming (in our case, using Reactive MongoDB database integration).

---

## Quickstart

### Installation

- Maven
- Java 21
- Docker
- Serverless


### Local execution

#### 1º Compilation

- Maven

  `mvn clean install`


#### 2º Execution

(No certs needed in our JDK)

- Create Docker image

  `docker build -t management-tool .`


- Run it

  `docker compose up --build`


- Test

After deploy/run the docker image, you can use the Postman collection "CUSTOMER TOOL.postman_collection.json" (in the repo) to test the services.

- Stop it

  `docker compose down`

If you want to drop the MongoDB Docker volume: `docker compose down -v` or `docker volume rm mongo-data`


#### Execution integration Tests

`mvn clean test`

---

## Technology Stack

- **Language & Framework**: Java 21 with Spring Boot
- **Architecture**: Hexagonal Architecture
- **Communication Interfaces**: RESTFul
- **Database**: MongoDB (Reactive)
- **Containerization**: Docker
- **Deployment**: Serverless (AWS Lambda approach)

---

## Project structure

Hexagonal architecture (domain, application and infrastructure layers).

---

## Contributing

Contributions are welcome! Here the GitHub repo https://github.com/RVCK/reactive-management-tool

---