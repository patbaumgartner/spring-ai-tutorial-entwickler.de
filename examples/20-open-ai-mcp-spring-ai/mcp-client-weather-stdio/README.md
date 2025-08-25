# Requesting a Weather Forecast for Germany

**Note:** Not all weather stations provide data. In the examples, "Kempten" is used because data is available there.

Once the client is running, it will listen on port `8081`.

You can then submit questions by sending a POST request to the `/ask` endpoint with a JSON payload containing a "question" property.

## Examples using `curl`

### Weather forecast for Kempten tomorrow

Bash

```bash
curl -H "Content-Type: application/json" \
     -X POST http://localhost:8081/ask \
     -d '{"question":"What is the weather forecast for Kempten for tomorrow?"}'
```

Powershell

```shell
curl -Uri http://localhost:8081/ask `
     -Method POST `
     -Headers @{ "Content-Type" = "application/json" } `
     -Body '{ "question": "What is the weather forecast for Kempten for tomorrow?" }'
```

### Weather forecast for Kempten this weekend

Bash

```bash
curl -H "Content-Type: application/json" \
     -X POST http://localhost:8081/ask \
     -d '{"question":"What will the weather be like in Kempten this weekend?"}'
```

Powershell

```shell
curl -Uri http://localhost:8081/ask `
     -Method POST `
     -Headers @{ "Content-Type" = "application/json" } `
     -Body '{ "question": "What will the weather be like in Kempten this weekend?" }'
```

### Weather forecast for a specific date in Kempten

Bash

```bash
curl -H "Content-Type: application/json" \
     -X POST http://localhost:8081/ask \
     -d '{"question":"Will it rain in Kempten on May 22th?"}'
```

Powershell

```shell
curl -Uri http://localhost:8081/ask `
     -Method POST `
     -Headers @{ "Content-Type" = "application/json" } `
     -Body '{ "question": "Will it rain in Kempten on May 22th?" }'
```
