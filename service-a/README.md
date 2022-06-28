# Service-A

Provides capabilities to manage the saving account `A`.

## How to run the project

### Requirements

1. Java 17+
2. Docker

### How to start the project

- Run `docker-compose up -d` from the root of the repository to start all necessary external dependencies - PostgreSQL
  in this case.
- Run `./gradlew bootRun` from the root of the repository to start the application
- The gateway should be available at `http://localhost:8081/`

### How to test

- Execute command `curl http://localhost:8081/savings/a/balance` to get the balance of the saving account.
- Execute
  command `curl -X POST http://localhost:8081/savings/a/balance -H 'Content-Type: application/json' -d '{"amount": 100}'`
  to deposit `amount` to the saving account. Change the `amount` value to deposit/credit a different amount.
