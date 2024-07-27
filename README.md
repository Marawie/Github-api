# GitHub Repositories API

## Opis

To jest aplikacja oparta na Spring Boot, która pozwala na listowanie wszystkich repozytoriów GitHub użytkownika, które nie są forkami. Dodatkowo zwraca informacje o gałęziach i ostatnim commit sha.

## Wymagania

- Java 17 lub nowsza
- Maven
- Konto GitHub z wygenerowanym [personal access token](https://github.com/settings/tokens)

## Instalacja

1. Sklonuj to repozytorium:
    ```sh
    git clone https://github.com/yourusername/github-repos-api.git
    cd github-repos-api
    ```

2. Dodaj swój token GitHub do pliku `src/main/resources/application.properties`:
    ```properties
    github.token=your_github_token_here
    ```

## Uruchomienie

Aby uruchomić aplikację, wykonaj następujące polecenie:

```sh
./mvnw spring-boot:run
