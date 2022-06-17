# Todo System

### Authors

Yaniv Krol

Ron Rachev

## Abstract

This is an implemenation of a Todo system using Java and the Spring Framework.

The system enables managing a list of persons, tasks, and assignning tasks to people.

The system is built on top of 3 layers - Model View Controller, Service Layer, and JPA (Persistance Abstraction Layer).

API description: https://mbarsinai.com/files/bgu/2022a/miniproj/swagger/

## System Design

### Model View Controller

There are 2 controllers, one for managing people, availabe on `"/api/people"`, and one for maanging tasks, avialble on `"/api/tasks"`. The controllers provide various methods for CRUD operation on the objects, such as `GET`, `POST`, `DELETE`, etc.

The controllers also provide a lot of execption handling methods, such as when a requested object is not found, automatically transforming the rersponse type accordingly, e.g. status 404 for not found.

### Service Layer

The service layers provides the business logic and interaction with the repositories. In this system, we have 2 services, `PeopleService` and `TasksService`, each handling their respective responsibilities.

### JPA

This is an abstraction layer for the H2 embedded database. This abstraction provides us with easy Java methods that replace traditional SQL queries, such as `deleteById(String)`.

In this system, we have 2 repositores, `TaskRepo` and `PersonRepo`, where all the tasks are stored in a tasks table which is managed by `TaskRepo` and the same goes for `PersonRepo`.

## How to Run

1. Install Java
2. Install maven
3. clone this respository `$ git clone https://github.com/eNtrozx/todosys`
4. cd into the directory `$ cd todosys`
5. Run `$ mvn package`
6. Run `$ java -jar target/todosys-1.0.0.jar`
7. The application will be available on port `8080`
