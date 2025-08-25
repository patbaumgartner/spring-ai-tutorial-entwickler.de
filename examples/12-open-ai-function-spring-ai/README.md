# OpenAI calls Spring AI

## Exploring Function Calling

1. Again we have two implementations. One using the `ChatClient` and one using the `ChatModel`.
2. On the Request object we have defined some hints using `@JsonPropertyDescription` to give the AI a hint on what to set.
3. Is there a difference when running the application if there is no `@JsonPropertyDescription("Local unit to measure air temperature like F for Fahrenheit countries with the imperial system and C for degree Celsius in countries with metric system.") Unit unit` defined?
4. You may see some differences if you customise the prompt or descriptions.
