# Simple Gateway Service

Main responsibilities are to route requests to the appropriate service based on the request url,
handle timeouts, retries and log incoming requests.

## How to run the project

### Requirements

1. Java 17+

### How to start the project

- Run the following command `./gradlew bootRun`
- The gateway should be available at `http://localhost:8083/`

### Where to get logs?

- Logs are stored inside a `./logs` directory
- Logs configuration can be found in `./src/main/resources/logback.xml` directory

### How to test timeout?

- Check the `com.example.gateway.GatewayTimeoutIntegrationTest` test class.
- Basic idea is to start MockWebService with timeout of `>5` seconds and then check if the Gateway returns the correct
  error message.
- Timeout configuration can be found in `./src/main/resources/application.yaml` file

