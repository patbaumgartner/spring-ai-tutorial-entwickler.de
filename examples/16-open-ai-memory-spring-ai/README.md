# Calling OpenAI from Spring AI

## Exploring ChatMemory

1. The AI models REST API is stateless. Even if you have a history in the ChatGPT chat window.
2. One way to achieve the same is to use a `ChatMemory`.
3. Depending on the use case it may even be session scoped.
4. The `MessageWindowChatMemory` is one implementation, which is the other? (See the documentation!)
5. There is also an abstraction for `ChatMemoryRepository`. What is it?
6. Look at the code and run the application. What do you see?
