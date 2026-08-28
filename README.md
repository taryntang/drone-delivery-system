# 🚁 Drone Delivery System

A full-stack drone delivery management application built with **Java and Spring Boot** that simulates the operations of a drone-based delivery platform.

The system manages stores, customers, drones, pilots, inventory, and delivery orders through a centralized web application. It was developed as a team software engineering project with an emphasis on **object-oriented design, system architecture, persistence, and multi-user application development**.

## Overview

The Drone Delivery System models the workflow of a commercial drone delivery platform.

Users can manage:

- Stores and inventory
- Customers
- Delivery drones
- Drone pilots
- Customer orders
- Order items
- Drone delivery operations

The application coordinates these components while maintaining the relationships between stores, customers, pilots, drones, inventory, and active orders.

## Tech Stack

**Backend**
- Java
- Spring Boot
- Spring MVC
- Spring Data JPA
- Maven

**Frontend**
- HTML
- CSS
- Server-side web templates

**Data & Infrastructure**
- JPA persistence layer
- Repository pattern
- Docker
- Docker Compose

**Testing**
- JUnit
- Spring Boot Test

## Architecture

The application follows a layered architecture:

```text
Browser / User
      │
      ▼
┌─────────────────────┐
│   Web Interface     │
│   HTML / CSS        │
└─────────┬───────────┘
          │
          ▼
┌─────────────────────┐
│ Controllers         │
│ Spring MVC          │
└─────────┬───────────┘
          │
          ▼
┌─────────────────────┐
│ Service Layer       │
│ Business Logic      │
└─────────┬───────────┘
          │
          ▼
┌─────────────────────┐
│ Repository Layer    │
│ Spring Data JPA     │
└─────────┬───────────┘
          │
          ▼
┌─────────────────────┐
│ Domain / Data Model │
│ Customer, Drone,    │
│ Store, Order, etc.  │
└─────────────────────┘
```

This separation keeps presentation logic, business rules, and data access organized and maintainable.

## Core Features

### Store Management
Create and manage stores and their available inventory.

### Customer Management
Maintain customer information and support customer-specific ordering workflows.

### Drone Management
Register drones and manage their operational assignments.

### Pilot Management
Maintain drone pilot information and associate pilots with drone operations.

### Order Management
Create, view, update, cancel, and process customer orders.

### Delivery Operations
Coordinate stores, orders, pilots, and drones to simulate the delivery workflow.

## Project Structure

```text
drone-delivery-system/
│
├── deliveryservice/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/
│   │   │   │   └── edu/gatech/deliveryservice/
│   │   │   │       ├── configuration/
│   │   │   │       ├── controller/
│   │   │   │       ├── jpa/
│   │   │   │       ├── repository/
│   │   │   │       ├── service/
│   │   │   │       ├── threadexecutor/
│   │   │   │       └── viewcontroller/
│   │   │   │
│   │   │   └── resources/
│   │   │       ├── css/
│   │   │       ├── templates/
│   │   │       └── application.properties
│   │   │
│   │   └── test/
│   │
│   ├── Dockerfile
│   ├── docker-compose.yml
│   └── pom.xml
│
└── README.md
```

## Getting Started

### Prerequisites

Make sure you have the following installed:

- Java
- Maven or Maven Wrapper
- Docker *(optional)*

### Run with Maven

Navigate to the application directory:

```bash
cd deliveryservice
```

On macOS/Linux:

```bash
./mvnw spring-boot:run
```

On Windows:

```bash
mvnw.cmd spring-boot:run
```

Then open the application in your browser using the local address configured by the Spring Boot application.

### Run with Docker

The project also includes Docker configuration:

```bash
cd deliveryservice
docker compose up --build
```

## Testing

Run the automated tests with:

```bash
cd deliveryservice
./mvnw test
```

On Windows:

```bash
mvnw.cmd test
```

## Software Engineering Concepts Demonstrated

This project demonstrates practical experience with:

- Object-oriented programming
- Layered software architecture
- MVC design
- Web controller development
- Repository pattern
- Database persistence with JPA
- Dependency injection
- Multi-user application workflows
- Automated testing
- Maven dependency management
- Docker containerization
- Team-based software development
