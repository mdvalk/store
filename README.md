# Jumbo Store API

This microservice provides an API to interact with Jumbo stores.
A user can use this application to request the 5 nearest Jumbo stores to a given location.

The flow is: 
 - User uses GET /store endpoint and provides a point location
 - The application will query the PostgreSQL database to retrieve the 5 closest stores to the given point location.
 - Result is returned to the user in JSON format

For more information see the section `API Documentation`.


## Developing

For running the application locally (to test with the swagger-ui for example), a docker-compose file is present in the project. Keep in mind
that this docker-compose file won't be used in production. The database container present in this compose file is only used for development
purposes, the python script as well as the stores.json file present would not be used for any deployment in a real life scenario.



### Prerequisites

You will need the following tools on your local development environment in order to run the application and bootstrap the required external services (e.g.: database):
 - Java 21 SDK
 - Maven 3
 - Docker

Make sure port 8080 and 5432 are available on your system.

### How to start


Use Docker to start the PostgreSQL database and seed it with the data as provided by the stores.json:
1. From the root of the project start the database: ```$ docker compose up --build -d```
   - If a local database is already present, first remove it by running:
      ```docker compose down``` 
2. Check with `docker ps` to verify that the database is running
3. You can optionally check that seeding the database succeeded by connecting to the database using your preferred database client (IntelliJ has great support) and connection details can be found in the local properties file.
4. In case the database did not start correctly please refer to the `Troubleshooting` section, otherwise continue with the below instructions to start the Java application.

Start the Java application:
1. The application requires the follow properties to be configured
    - `spring.datasource.url`
    - `spring.datasource.username`
    - `spring.datasource.password`
2. Make sure to run the application using the `local` profile.
    - For simplicity a `application-local.properties` file has been supplied with the correct credentials in order to connect to the database locally.
3. Build the app ```$ mvn verify``` and verify that the build as well as all tests succeed
4. Run the app locally, use the local profile


### Troubleshooting

The development setup should be operating system agnostic as much as possible but it was designed on a Linux environment so there's a possibility for issues on a Windows environment.
The most likely culprit are the file-paths in the `docker-compose.yml` file on line 23 and 31.

In case the database did not start correctly, or the data is not seeded into the database:
 - Run `docker compose logs` from the root of the project in order to see possible errors in the docker containers.

If you cannot find a solution feel free to contact the author (me) directly and i'll gladly help out! 


### Api documentation

We use swagger for API documentation and local manual testing. To open it navigate to:
http://localhost:8080/swagger-ui.html

The OpenAPI specification can be found at http://localhost:8080/v3/api-docs

## Building

This project uses Java 21 and Maven as build tool, you can use `mvn clean install -DskipTests` in order to build the application without running tests.


## Testing

Testing is both done by unit testing and integration testing.

In order to run both unit tests and integration tests please use `mvn clean verify`

## Health

Application health endpoints are exposed using actuator and can be found at the following endpoints:
 - http://localhost:8080/actuator
 - http://localhost:8080/actuator/health
