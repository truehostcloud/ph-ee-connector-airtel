# Airtel Money Connector

Payment Hub Connector containing Zeebe workers that are responsible for communicating with [Airtel
Money APIs](https://developers.airtel.africa/documentation). It is a part of the OAF Payment Hub EE
setup. See
the [Payment Hub EE documentation](https://mifos.gitbook.io/docs/payment-hub-ee/overview)
for more information about Zeebe projects and the Payment Hub in general.

[![Build Status](https://dev.azure.com/OAFDev/prd-pipelines/_apis/build/status%2Fone-acre-fund.ph-ee-connector-airtel?branchName=main)](https://dev.azure.com/OAFDev/prd-pipelines/_build/latest?definitionId=179&branchName=main)

## Tech Stack

- Java 17
- Spring Boot
- Apache Camel
- Zeebe Java Client

## Getting Started

Clone the project

  ```bash
    git clone https://github.com/one-acre-fund/ph-ee-connector-airtel.git
    cd ph-ee-connector-airtel
  ```

This connector is expected to be run alongside other connectors/services. It depends on some of
those services being up
and healthy. For local development, the services that are most critical for running this project
have been included in
the `docker-compose.yml` file. The following components are included:

- Zeebe: A workflow engine for microservices orchestration. This must be running in a healthy state
  otherwise errors
  will occur when the services below attempt to connect to it.
- Zeebe-ops: Provides APIs for carrying out certain operations on zeebe such as uploading a bpmn
  file
- Channel-connector: Provides APIs for initiating collection requests
- Erply-connector: An Account Management System (AMS) connector for Erply. Any AMS connector can be
  used here

A lot more services can be added to the above based on your needs, but to run this connector
locally,
the ones listed above are the required minimum.
Please note that the `docker-compose.yml` file in this repository should NOT be used in a production
environment.

## Running with Docker

Some images listed in the `docker-compose.yml` are available on OAF's Azure Container Registry (
ACR). To be able to pull
them, certain permissions must be granted to your azure account. Follow the steps below to
successfully run the project:

- Ensure [Docker](https://docs.docker.com/get-docker/) is installed on your machine

- Authenticate with
  azure. [Install the Azure CLI](https://learn.microsoft.com/en-us/cli/azure/install-azure-cli)
  on your machine if it's not already available, and then run the command below

  ```bash
      az acr login -n oaftech # Log in to OAF's ACR through the Docker CLI.
   ```

- Run the project:

  Update `src/main/resources/application.yml` with the appropriate values where necessary, or
  provide the
  values as environment variables in the `services.airtel-connector.environment` section of
  the `docker-compose.yml`
  file, and run the command below:

  ```bash
      docker compose up -d
   ```

## Usage

To initiate a collection request, and have the airtel connector handle the transaction, follow the
steps below:

- Upload the erply bpmn (found in `src/main/resources/airtel_flow_erply-oaf.bpmn`) through *
  *zeebe-ops** by sending a
  POST
  request to `http://localhost:5001/zeebe/upload` with the file attached.

- Send a collection request through the **channel-connector** by sending a POST request
  to `http://localhost:5002/channel/collection`
  with a sample body as shown below:
  ```json
  {
    "payer": [
        {
            "key": "MSISDN",
            "value": "250730000000"
        },
        {
            "key": "WALLETID",
            "value": "60649568"
        }
    ],
    "amount": {
        "amount": "20",
        "currency": "RWF"
    }
  }
  ```
- Check the logs in the **airtel-connector** container to see that the tasks got executed
  successfully

## Troubleshooting

If an error occurs while carrying out any of the steps above, check if the zeebe container is in a
healthy state by
either viewing its state through `docker ps` or sending a GET request
to `http://localhost:9600/health`.
If the zeebe container shows state as unhealthy or the health endpoint doesn't return a 204 status
response, restart the
zeebe container.
