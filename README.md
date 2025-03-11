# 📝 ToDoList - Application Spring Boot

Une application de gestion de tâches (ToDo List) développée avec **Java Spring Boot**, avec une architecture propre basée sur des contrôleurs, services, entités, DTOs, et sécurisée via JWT.

## 📂 Structure du projet

- `controllers/` - Contrôleurs REST (ex: `TaskController`)
- `services/` - Logique métier (ex: `TaskService`, `TaskServiceImpl`)
- `entities/` - Entités JPA (ex: `Task`, `User`, etc.)
- `repositories/` - Interfaces pour la persistance des données
- `models/` - DTOs et Forms pour les échanges de données
- `utils/` - Filtres, JWT, pagination, spécifications dynamiques, etc.
- `configs/` - Configuration de sécurité et Swagger/OpenAPI
- `resources/` - Fichiers de configuration (`application.properties`)
- `test/` - Tests unitaires

## 🔐 Sécurité

L'application utilise :
- **JWT (Json Web Token)** pour l'authentification
- **Filtres personnalisés** pour le traitement des requêtes (ex: `JwtFilter`, `UserFilter`)
- **Spring Security** configuré via `SecurityConfig`

## 📦 Dépendances

- Spring Boot
- Spring Security
- Spring Data JPA
- Lombok
- JWT
- Swagger / OpenAPI
- H2 / PostgreSQL (selon la config)

## 🚀 Démarrage

### Prérequis :
- Java 17+
- Maven

### Lancer l'application :
```bash
./mvnw spring-boot:run
```

### Accès API :
- Swagger UI : `http://localhost:8080/swagger-ui.html`
- API : `http://localhost:8080/api/...`


## 📁 Exemple de modèles

### `Task` :
```json
{
  "title": "Faire les courses",
  "description": "Acheter du lait, pain et œufs",
  "dueDate": "2025-03-15",
  "completed": false
}
```

### `User` :
```json
{
  "username": "alice",
  "password": "secure123",
  "role": "USER"
}
```

## ✍️ Auteur
Projet développé par Projet développé par [Barnabé Dussart](https://github.com/AtomDus)
