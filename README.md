# Filmothèque

[![CI](https://github.com/Yohan-Baechle/filmotheque/actions/workflows/ci.yml/badge.svg)](https://github.com/Yohan-Baechle/filmotheque/actions/workflows/ci.yml)

Application web de gestion de films développée avec **Spring Boot 3** et
architecture en couches. Projet pédagogique réalisé durant ma formation
**Concepteur Développeur d'Applications (CDA)** à l'ENI.

## Fonctionnalités

- Catalogue de **films** (titre, année, durée, synopsis, genre)
- Gestion des **genres** et des **participants** (acteurs, réalisateurs)
- Gestion des **membres** et authentification via Spring Security
- Ajout et consultation de films avec redirection vers la liste

## Architecture

```
bo/           Objets métier (Film, Genre, Membre, Participant)
dal/          Couche d'accès aux données (implémentations JDBC + bouchons)
bll/          Couche métier (services)
controllers/  Contrôleurs Spring MVC + convertisseurs
exceptions/   Exceptions métier (ex. FilmNotFound)
```

La couche DAL fournit à la fois des implémentations **JDBC** et des **bouchons**
(mock en mémoire), ce qui permet de développer et tester l'IHM indépendamment de
la base de données.

## Stack technique

- **Java 17**
- **Spring Boot 3.2** (Web, Security, Validation, JDBC)
- **Thymeleaf** pour les vues
- **Gradle** (wrapper inclus)

## Mise en route

Prérequis : JDK 17. Le schéma de base est fourni dans [`sql/create-db.sql`](sql/create-db.sql).

```bash
./gradlew bootRun
```

Application accessible sur http://localhost:8080.

## Build

```bash
./gradlew build
```

## Licence

Distribué sous licence MIT. Voir [LICENSE](LICENSE).
