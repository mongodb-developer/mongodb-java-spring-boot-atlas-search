# Java Spring Boot Template with MongoDB Atlas Search

This project is a template for a java Spring Boot application with MongoDB Atlas Search using Spring Data MongoDB.

For more information about this repository, consult the related [blog post](https://www.baeldung.com/mongodb-spring-data-atlas-search) and video.

# Video

You can watch this video to learn more about this project.

[![Java Spring Boot Template with MongoDB Atlas Search video](http://img.youtube.com/vi/OERQ4p1Uhbs/0.jpg)](http://www.youtube.com/watch?v=OERQ4p1Uhbs "How to Build a REST API with Java, Spring Boot, Spring Data, and MongoDB Atlas Search")

# Prerequisites

- Java 17.
- [MongoDB Cluster](https://www.mongodb.com/atlas/database) v6.0.12 or higher on MongoDB Atlas (works with an M0 free
  cluster).
- Load the [sample datasets](https://www.mongodb.com/docs/atlas/sample-data/#available-sample-datasets) as we are
  working with the `sample_mflix.movies` collection.
- Create an Atlas Search index for the `sample_mflix.movies` collection named `dynamic` with the following
  configuration:

```json
{
  "mappings": {
    "dynamic": true
  }
}
```

# Getting Started

Update the `application.properties` with your values.

```properties
spring.data.mongodb.uri=${MONGODB_URI:mongodb://localhost}
spring.data.mongodb.database=sample_mflix
spring.data.mongodb.atlas.search.index=dynamic
```

For Linux and macOS.

```bash
export MONGODB_URI="mongodb+srv://<USER>:<PWD>@free.abcde.mongodb.net/?retryWrites=true&w=majority"
./mvnw clean spring-boot:run
```

For PowerShell.

```bash
$env:MONGODB_URI="mongodb+srv://<USER>:<PWD>@free.abcde.mongodb.net/?retryWrites=true&w=majority"
mvnw.cmd clean spring-boot:run
```

# Jar File

```bash
./mvnw clean package
java -jar target/java-spring-boot-mongodb-atlas-search-1.0.0.jar
```

# Test the REST API

## cURL

```bash
curl -vs "http://localhost:8080/movies/with/indiana%20jones?limit=2" | python3 -m json.tool
```

```json
*   Trying [::1]:8080...
* Connected to localhost (::1) port 8080
> GET /movies/with/indiana%20jones?limit=2 HTTP/1.1
> Host: localhost:8080
> User-Agent: curl/8.4.0
> Accept: */*
> 
< HTTP/1.1 200 
< Content-Type: application/json
< Transfer-Encoding: chunked
< Date: Thu, 30 Nov 2023 01:28:09 GMT
< 
{ [1538 bytes data]
* Connection #0 to host localhost left intact
[
    {
        "fullplot": "Indiana Jones, famed adventurer and archaeologist acquires a diary that holds clues and a map with no names to find the mysterious Holy Grail- which was sent from his father, Dr. Henry Jones, in Italy. Upon hearing from a private collector, Walter Donavan, that the mission for the Holy Grail went astray with the disappearance of his father, Indiana Jones and museum curator Marcus Brody venture to Italy in search of Indy's father. However, upon retrieving Dr. Henry Jones in Nazi territory, the rescue mission turns into a race to find the Holy Grail before the Nazis do- who plan to use it for complete world domination for their super-race. With the diary as a vital key and the map with no names as a guide, Indiana Jones once again finds himself in another death defying adventure of pure excitement.",
        "imdb": {
            "rating": 8.3
        },
        "year": 1989,
        "title": "Indiana Jones and the Last Crusade"
    },
    {
        "fullplot": "The year is 1936. An archeology professor named Indiana Jones is venturing in the jungles of South America searching for a golden statue. Unfortunately, he sets off a deadly trap but miraculously escapes. Then, Jones hears from a museum curator named Marcus Brody about a biblical artifact called The Ark of the Covenant, which can hold the key to humanly existence. Jones has to venture to vast places such as Nepal and Egypt to find this artifact. However, he will have to fight his enemy Rene Belloq and a band of Nazis in order to reach it.",
        "imdb": {
            "rating": 8.6
        },
        "year": 1981,
        "title": "Raiders of the Lost Ark"
    }
]
```

## Swagger 3

- Swagger 3 is already configured in this project.
- The Swagger UI can be seen
  at [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html).
- The JSON Open API documentation 3.0.1 is at [http://localhost:8080/v3/api-docs](http://localhost:8080/v3/api-docs).
- The YAML Open API documentation 3.0.1 is
  at [http://localhost:8080/v3/api-docs.yaml](http://localhost:8080/v3/api-docs.yaml).
- You can also try the entire REST API directly from the Swagger interface!

# Author

Maxime Beugnet

- maxime@mongodb.com
- MaBeuLux88 on [GitHub](https://github.com/mabeulux88)
- MaBeuLux88 in the [MongoDB Developer Community forum](https://www.mongodb.com/community/forums/u/MaBeuLux88/summary).
