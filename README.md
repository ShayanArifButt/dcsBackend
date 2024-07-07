DCS - Digital Charging Solutions GmbH 

Description
This is a Spring Boot application developed in Kotlin for managing Charge Data Records (CDR) for electric vehicles. It uses a PostgreSQL database and provides a REST API to manage and retrieve CDRs.

Prerequisites
Java 19
Docker
Docker Compose


Building and Running the Application
Using Docker
Build the Docker Image

To build the Docker image, run the following command:

docker-compose build

To run the application, use the following command:

docker-compose up

This will start the application on port 8080 and a PostgreSQL database on port 5432. The backend service will automatically connect to the PostgreSQL database.

Without Docker:

If you prefer to run the application without Docker, follow these steps:

Setup PostgreSQL

Ensure you have PostgreSQL installed and running. Create a database named dcs and a user with the username postgres and password root.

Update Application Properties

Update the src/main/resources/application.properties file with your PostgreSQL configuration:

Build the Application

Use Maven to build the application:

mvn clean package -DskipTests

Run the application using the following command:

java -jar target/dcs-0.0.1-SNAPSHOT.jar

JWT Authentication

JWT (JSON Web Token) is used to secure the endpoints of this application. Users must be authenticated to access the endpoints other than the login endpoint. After a successful login, the server issues a JWT, which the client must include in the Authorization header of subsequent requests.

Login Endpoint
URL: /login
Method: POST
Request Body:

{
"username": "testUser",
"password": "testPassword"
}

Response:

{
"token": "your_jwt_token"
}

Secured Endpoints
For accessing secured endpoints, include the JWT in the Authorization header as follows:

Authorization: Bearer your_jwt_token

API Endpoints

Create a Charge Data Record
URL: /api/cdr
Method: POST
Request Body:

json

{
"chargingSessionId": "abc123",
"vehicleId": "veh456",
"startTime": "2024-06-18T08:30:00Z",
"endTime": "2024-06-18T10:30:00Z",
"totalCost": 15.75
}

Get a Charge Data Record by ID
URL: /api/cdr/{id}
Method: GET

Exception Handling
The application uses a global exception handler to manage errors and provide meaningful error messages.

Validation

The application includes custom validation to ensure data integrity, such as:

1)End time cannot be before start time.
2)Start time of a new record must be after the end time of the previous record for the same vehicle.