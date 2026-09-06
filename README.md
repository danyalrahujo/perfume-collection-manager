# Perfume Collection Manager
### Project for the Automation Software Testing course

[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=danyalrahujo_perfume-collection-manager&metric=alert_status)](https://sonarcloud.io/summary/new_code?id=danyalrahujo_perfume-collection-manager)
[![Vulnerabilities](https://sonarcloud.io/api/project_badges/measure?project=danyalrahujo_perfume-collection-manager&metric=vulnerabilities)](https://sonarcloud.io/summary/new_code?id=danyalrahujo_perfume-collection-manager)
[![Bugs](https://sonarcloud.io/api/project_badges/measure?project=danyalrahujo_perfume-collection-manager&metric=bugs)](https://sonarcloud.io/summary/new_code?id=danyalrahujo_perfume-collection-manager)
[![Code Smells](https://sonarcloud.io/api/project_badges/measure?project=danyalrahujo_perfume-collection-manager&metric=code_smells)](https://sonarcloud.io/summary/new_code?id=danyalrahujo_perfume-collection-manager)
[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=danyalrahujo_perfume-collection-manager&metric=coverage)](https://sonarcloud.io/summary/new_code?id=danyalrahujo_perfume-collection-manager)
[![Duplicated Lines (%)](https://sonarcloud.io/api/project_badges/measure?project=danyalrahujo_perfume-collection-manager&metric=duplicated_lines_density)](https://sonarcloud.io/summary/new_code?id=danyalrahujo_perfume-collection-manager)
[![Technical Debt](https://sonarcloud.io/api/project_badges/measure?project=danyalrahujo_perfume-collection-manager&metric=sqale_index)](https://sonarcloud.io/summary/new_code?id=danyalrahujo_perfume-collection-manager)
[![Coverage Status](https://coveralls.io/repos/github/danyalrahujo/perfume-collection-manager/badge.svg?branch=main)](https://coveralls.io/github/danyalrahujo/perfume-collection-manager?branch=main)

**Perfume Collection Manager** is a desktop application designed to help users organize and manage their perfume collection through a simple graphical user interface. Users can add new perfumes by providing information such as a unique ID, name, brand, fragrance family, volume, and rating. The application allows users to view, update, and delete perfumes at any time.

The application uses **MongoDB** for persistent storage and provides a graphical interface developed using **Java Swing**. The project follows a modular structure using a model, repository, controller, and view, with separate components for application logic and data access.

The project includes automated **unit, integration, and end-to-end tests**. Mockito is used for mocking dependencies, AssertJ Swing is used for testing the graphical user interface, and Testcontainers is used to run MongoDB integration tests in Docker.

Code quality and test effectiveness are monitored using **JaCoCo, SonarCloud, Coveralls, and PIT Mutation Testing**. The project is also built and tested automatically through **GitHub Actions** using Java 17 and Maven.
