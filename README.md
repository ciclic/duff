# Ciclic challenge

The challenge consists creating a microservice project which is possible to execute CRUD operations and also retrieve a beer with a spotify playlist following some rules.

## Getting Started

The table illustrates which information we have about beers in the project.

|Estilo|Temperatura Ideal para consumo|
|:---:|:---:|
|Weissbier|-1° a 3°|
|Pilsens |-2° a 4°|
|Weizenbier |-4° a 6°|
|Red ale|-5° a 5°|
|India pale ale|-6° a 7°|
|IPA|-7° a 10°|
|Dunkel|-8° a 2°|
|Imperial Stouts|-10° a 13°|
|Brown ale|0° a 14°|

The project should have the endpoint to execute operations like creating, modifying, deleting and retrieving beers.

### Prerequisites

- [Github](https://git-scm.com/book/en/v2/Getting-Started-Installing-Git)
- [Docker](https://docs.docker.com/install/)
- [Docker compose](https://docs.docker.com/compose/install/#prerequisites)
- [Java 1.8](https://www.oracle.com/technetwork/pt/java/javase/downloads/index.html)
- [SDKman](https://sdkman.io/)
- [Valid Spotify API key](https://developer.spotify.com/documentation/web-api/)

### Installing

Clone the project

```
git clone git@github.com:gustavotsuji/duff.git
cd duff
```

Execute docker compose to run database

```
docker-compose up
```

Start the project (oh, I wasn't able to start project from docker... =( )

Generate a valid API key following Spotify instructions

```
./gradlew bootRun
```

## Running the tests

```
./gradlew test
```

The test reports is available at `build/reports/tests/test/index.html`

## Documentation

```
./gradlew build ascii
```

The API documentation id available at `build/reports/tests/test/index.html`
