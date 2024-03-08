# Clustered Data Warehouse

Clustered Data Warehouse is a project developed as part of a Scrum team for Bloomberg to analyze FX (Foreign Exchange) deals. The primary goal of this project is to accept deal details from various sources and persist them into a database, making the data available for further analysis.

## Project Description

### Request Logic

The project handles incoming FX deal requests with the following key fields:

- Deal Unique Id
- From Currency ISO Code (Ordering Currency)
- To Currency ISO Code
- Deal Timestamp
- Deal Amount in Ordering Currency

The request data is subject to validation, which includes checking for missing fields and ensuring the correct data types. While the project may not cover all possible validation cases, it demonstrates how to implement such validations as shown below.

### Key Features

- The system ensures that the same deal is not imported more than once.
- No rollback is allowed, meaning that once a deal is imported, it is saved in the database.
- The project provides a workable deployment environment using Docker Compose.
- The source code is organized as a Maven or Gradle project.
- Proper error and exception handling mechanisms are implemented.
- Comprehensive logging is in place to capture relevant information.
- Unit testing is performed, and code coverage is maintained to ensure the correctness of the application.
- The project is hosted on GitHub for collaboration and version control.

## Getting Started

To get started with the Clustered Data Warehouse project, follow these steps:

1. **Clone the Project**: Clone the project repository from GitHub.

    ```shell
    git clone https://github.com/GhazalOmar/ClusteredData-Warehouse/
    ```



2. **Build the Project**: Build the project using Maven or Gradle, depending on your preference.

   Using Maven:

    ```shell
    mvn clean install package
    ```

   Using Gradle:

    ```shell
    ./gradlew clean build
    ```


3. **Run the Application with Docker Compose**: Use Docker Compose to start the application and the associated database container.

    ```shell
    docker-compose up
    ```

   Docker Compose will start the necessary containers and link them together to create a fully operational environment.


4. **Access the Endpoints**:

- **Save a Deal**:

  Endpoint: `POST /api/v1/deal/save`

  Use this endpoint to save a new FX deal. Send a POST request with a valid JSON body, for example:

    ```json
    {
      "id": "2",
      "fromISOCurrency": "JOD",
      "toISOCurrency": "USD",
      "dealTimestamp": "2022-8-04T09:22:32",
      "amount": 0.1
    }
    ```

  **Sample Output**:

    ```json
    {
        "message": "Deal with id [2] successfully saved",
        "statusCode": "CREATED"
    }
    ```

- **Get All Deals**:

  Endpoint: `GET /api/v1/deal/fetch-all`

  Use this endpoint to retrieve all FX deals. Send a GET request to this endpoint.

  **Sample Output**:

    ```json
    {
        "message": "Deals were retrieved successfully",
        "statusCode": "OK",
        "data": [
            {
              "id": "2",
              "fromISOCurrency": "JOD",
              "toISOCurrency": "USD",
              "dealTimestamp": "2023-8-04T09:22:32",
              "amount": 0.1
            },
            {
              "id": "3",
              "fromISOCurrency": "JOD",
              "toISOCurrency": "KWD",
              "dealTimestamp": "2023-8-04T10:22:32",
              "amount": 0.1
            }
        ]
    }
    ```

- **Get a Deal by ID**:

  Endpoint: `GET /api/v1/deal/fetch/{id}`

  Use this endpoint to retrieve a specific FX deal by its unique ID. Replace `{deal_id}` with the actual deal ID you want to fetch.

  **Sample Output**:

    ```json
    {
        "message": "Deal with id [2] is successfully retrieved",
        "statusCode": "OK",
        "data": {
            "dealId": 2,
            "fromCurrency": "USD",
            "toCurrency": "JOD",
            "dealTimestamp": "2023-10-04T12:34:56",
            "dealAmount": 50.25
        }
    }
    ```


5. **Testing in Postman**:

   ```
      curl --location 'http://localhost:8080/api/v1/deal/save' \
    --header 'Content-Type: application/json' \
    --data '{
      "id": "2",
      "fromISOCurrency": "JOD",
      "toISOCurrency": "USD",
      "dealTimestamp": "2022-8-04T09:22:32",
      "amount": 0.1
    }'
   ```
