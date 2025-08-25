# Abfrage einer Wettervorhersage für Deutschland

**Hinweis:** Nicht alle Wetterstationen liefern Daten. In den Beispielen wird "Kempten" verwendet, da dort Daten verfügbar sind.

Sobald der Client läuft, hört er auf Port `8081`.

Sie können dann Fragen stellen, indem Sie eine POST-Anfrage an den `/ask`-Endpunkt senden, mit einem JSON-Payload, der eine "question"-Eigenschaft enthält.

## Beispiele mit `curl`

### Wettervorhersage für Kempten morgen

Bash

```bash
curl -H "Content-Type: application/json" \
     -X POST http://localhost:8081/ask \
     -d '{"question":"Wie ist die Wettervorhersage für Kempten für morgen?"}'
```

Powershell

```shell
curl -Uri http://localhost:8081/ask `
     -Method POST `
     -Headers @{ "Content-Type" = "application/json" } `
     -Body '{ "question": "Wie ist die Wettervorhersage für Kempten für morgen?" }'
```

### Wettervorhersage für Kempten am Wochenende

Bash

```bash
curl -H "Content-Type: application/json" \
     -X POST http://localhost:8081/ask \
     -d '{"question":"Wie wird das Wetter in Kempten am kommenden Wochenende?"}'
```

Powershell

```shell
curl -Uri http://localhost:8081/ask `
     -Method POST `
     -Headers @{ "Content-Type" = "application/json" } `
     -Body '{ "question": "Wie wird das Wetter in Kempten am kommenden Wochenende?" }'
```

### Wettervorhersage für ein bestimmtes Datum in Kempten

Bash

```bash
curl -H "Content-Type: application/json" \
     -X POST http://localhost:8081/ask \
     -d '{"question":"Wird es am 22. Mai in Kempten regnen?"}'
```

Powershell

```shell
curl -Uri http://localhost:8081/ask `
     -Method POST `
     -Headers @{ "Content-Type" = "application/json" } `
     -Body '{ "question": "Wird es am 22. Mai in Kempten regnen?" }'
```
