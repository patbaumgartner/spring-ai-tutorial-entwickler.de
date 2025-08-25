# Calling OpenAI from Spring AI With Guardrails

## Exploring the ChatClient Abstraction

1. Review the source files (Java, properties, and POM).
2. `SafeGuardAdvisor` is used to ensure that the response from the model is safe. 
3. Google provides a Guardrails API that can be used to create a custom `SafeGuardAdvisor`. You can find more information about it in the [Guardrails documentation](https://developers.google.com/checks/guide/ai-safety/guardrails).
4. There are other projects like [Guardrails AI](https://github.com/guardrails-ai/guardrails) that aim to provide similar functionality.
