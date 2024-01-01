# Test task - [Solution](#solution)

## Initial conditions:

1) Redis with stored transaction:
  - key - transaction id
  - value - metadata with json string

Commands to populate Redis:
- `set 123 '{"amount":100.05,"metadata":{"originatorId":1,"destinationId":2}}'`
- `set 124 '{"amount":150.75,"metadata":{"originatorId":10,"destinationId":20}}'`
- `set 125 '{"amount":1010.00,"metadata":{"originatorId":20,"destinationId":30}}'`
- `set 126 '{"amount":15.5,"metadata":{"originatorId":30,"destinationId":40}}'`

2) You receive transactions from Kafka topic. Example of a message in Kafka:
    ```json
    [
        {
          "PID": 123,
          "PAMOUNT": 94.7,
          "PDATA": 20160101120000
        },
        {   
          "PID": 123,
          "PAMOUNT": 94.7,
          "PDATA": 20160101120000
        },
        {   
          "PID": 124,
          "PAMOUNT": 150.75,
          "PDATA": 20160101120001
        },
        {   
          "PID": 125,
          "PAMOUNT": 1020.2,
          "PDATA": 20160101120002
        },
        {   
          "PID": 126,
          "PAMOUNT": 15.5,
          "PDATA": 20160101120003
        },
        {   
          "PID": 127,
          "PAMOUNT": 120.74,
          "PDATA": 20160101120004
        }
    ]
    ```

## Task:
You have to compare the `amount` field of the received transaction against the transaction in Redis. You should query Redis by transaction id.
Comparison has to be sent to another kafka topic, the message should contain enough information to understand the result.

## Requirements:
Take into account, that in future you:
* Might receive transactions not only from Kafka topic
* Might have to support several result message formats
* Might need to send check results via different channels, e.g. make an API call
  
# Solution

## Transaction Comparison App

The project is a Spring Boot application that facilitates the comparison of transaction data from a Kafka topic with stored transaction data in a Redis database.

## Table of Contents

- [Overview](#overview)
- [Key Features](#key-features)
- [Technologies Used](#technologies-used)
- [Project Structure](#project-structure)
- [Setup and Installation](#setup-and-installation)
- [Usage](#usage)

## Overview

This application serves to compare incoming transactions with stored ones and forwards the comparison results to another Kafka topic. It has been designed to accommodate potential changes in data sources, message formats, and communication channels.

## Key Features

- **Real-time Transaction Comparison**: Ensures data consistency by seamlessly comparing incoming Kafka transactions with stored Redis records.
  
- **Flexible Data Format Handling**: Easily adapts to diverse data formats, guaranteeing efficient processing and integration with various communication channels.
  
- **Automated Processing and Reporting**: Streamlines the comparison process, automating result forwarding and enhancing overall system efficiency.

## Future Scalability

As per the future requirements, this adaptable solution is primed for seamless scalability and reuse, making it a robust choice for evolving needs.

## Technologies Used

- Java 17
- Spring Boot 3.1.7
- Apache Kafka
- Redis
- Websocket
- Maven

## Project Structure

The project structure is organized as follows:

TransactionComparisonApplication    
│     
├── src     
│ ├── main       
│ │ ├── java     
│ │ │ ├── com   
│ │ │ │ ├── transactionservices   
│ │ │ │ │ └── ... (source code files)    
│ │ ├── resources     
│ │ │ ├── application.properties    
│ │ │ └── static   
├── pom.xml      
└── README.md     


## Setup and Installation

1. Clone the repository.
2. Build the project: `mvn clean`
3. Create the JAR file: `mvn package`
4. Run the application: `java -jar target/TransactionComparisonApplication.jar`

If the above setup doesn't work, follow these steps:

- Open the project in your IDE.
- To run the application and its dependencies, use the following command.
- First, navigate to the directory containing the `compose.yml` file, and then run the command: `docker compose up`
- This command ensures that all necessary dependencies are up and running before starting the application.
- Build and run the project using Maven.

## Usage

To utilize the application, use the following REST API endpoints:

### Redis Controller REST API

**Create Data:**

- Endpoint: `POST /data/{key}`
- Request Parameters: `key` (path variable), `value` (request body).
  [Click Here](TransactionComparisonApplication/src/main/resources/static/AddDataToRedis.png)
  

**Retrieve Data:**

- Endpoint: `GET /data/{key}`
- Description: Retrieve data from Redis.
- Request Parameters: `key` (path variable).

**Update Data:**

- Endpoint: `PUT /data/{key}`
- Description: Update data in Redis.
- Request Parameters: `key` (path variable), `value` (request body).

**Delete Data:**

- Endpoint: `DELETE /data/{key}`
- Description: Delete data from Redis.
- Request Parameters: `key` (path variable).

To publish a Kafka event, use the following REST API:

**Send Events:**

- Endpoint: `POST /kafka/publish`
- Description: Publishes a customer event to the Kafka topic.
- Request Body: JSON representation of the Kafka topic.


# To view the comparison results, open this URL in your browser: [http://localhost:9292](http://localhost:9292)

# Result

## Transaction Id is store in the Redis database : [Click Here](TransactionComparisonApplication/src/main/resources/static/ResultMatch.png)


## Transaction ID is present but the amount is not equal to the trasactoion amount from Redis database [Click Here](TransactionComparisonApplication/src/main/resources/static/AmountDiffer.png)


## Transaction Id is missing from the Redis database [Click Here](TransactionComparisonApplication/src/main/resources/static/MissingTransaction.png)

## Jar [Click Here](TransactionComparisonApplication/src/main/resources/static/Jar.png)


