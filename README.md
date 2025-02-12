# Currency Converter Application

This is a Spring Boot application that allows users to convert currencies and fetch exchange rates from an external API. The application supports retrieving current exchange rates and performing currency conversions. The code is designed to demonstrate how to build a currency converter using Spring Boot and integrating external APIs.

## Features

- **Get Exchange Rates**: Fetch the latest exchange rates for a given base currency.
- **Currency Conversion**: Convert an amount from one currency to another based on current exchange rates.
- **Error Handling**: Graceful handling of errors with appropriate status codes and custom error messages.
- **Unit Testing**: The service layer is tested using JUnit and Mockito.

## Technologies Used

- **Java 11+**
- **Spring Boot** for building the RESTful web services.
- **RestTemplate** for making HTTP requests to external APIs.
- **JUnit 5** for unit testing.
- **Mockito** for mocking HTTP requests in tests.
- **Maven** for dependency management and project build.

## Prerequisites

Before running this application, ensure you have the following installed:
- **Java 11 or higher** 
- **Maven** for dependency management and project builds.

## Setup and Installation

### 1. Clone the Repository

Clone this repository to your local machine:


git clone https://github.com/srinivas653/CurrencyConverter.git

### 2. Navigate to the Project Directory
Change into the project directory:

cd CurrencyConverter

### 3.Build the Project
Use Maven to install the necessary dependencies:

mvn clean install

### 4.Run the Application
You can start the application using the following command:

mvn spring-boot:run

The application will be accessible at http://localhost:8080.

### 5.. Get Exchange Rates
URL: /api/rates

Method: GET

Query Parameters:

base (optional): The base currency. Defaults to USD if not provided.

Example Request:

GET http://localhost:8080/api/rates?base=USD

Response:

json

{
  "rates": {
    "EUR": 0.85,
    "GBP": 0.75,
    "INR": 74.45,
    "AUD": 1.35
  }
}


### 6.2. Convert Currency
URL: /api/convert

Method: POST

Request Body:

json

{

  "from": "USD",
  "to": "EUR",
  "amount": 100
  
}

Example Request:


POST http://localhost:8080/api/convert

Content-Type: application/json

{

  "from": "USD",
  "to": "EUR",
  "amount": 100
  
}
Response:

json

{

  "from": "USD",
  "to": "EUR",
  "amount": 100,
  "convertedAmount": 85.0
  
}



