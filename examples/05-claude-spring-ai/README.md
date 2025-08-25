# Calling Anthropic Claude from Spring AI

## Exploring the ChatClient Abstraction

1. Examine the source files (Java, properties and POM).
2. Examine the user and system properties and the generated classes.
3. Do the generated classes compile? Why or why not?
4. Since Claude also returns text that is not valid JSON, we would need to instruct the model to make it fit.
5. Add `Simplify it as much as possible to fit the context window.` to the system prompt.
6. Try again and check the output.