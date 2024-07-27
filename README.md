# GitHub Repositories API

## Description

This is a Spring Boot-based application that allows you to list all of a user's GitHub repositories that are not forks. In addition, it returns branch information and the last commit sha.

## Requirements

- Java 17 or later
- Maven
- GitHub account with generated [personal access token](https://github.com/settings/tokens)

## Installation

1. Clone this repository:
    ```sh
    git clone https://github.com/Marawie/github-repos-api.git
    cd github-repos-api
    ```

2. Add your GitHub token to the `src/main/resources/application.properties`:
    ```properties
    github.token=your_github_token_here
    ```

## Launch

To start the application, run the following command:

```sh
./mvnw spring-boot:run
