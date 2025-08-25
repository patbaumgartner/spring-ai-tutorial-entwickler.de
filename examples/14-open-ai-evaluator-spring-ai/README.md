# Evaluating Ai Answers

## Evaluating for Relevancy and Correctness

1. Check the source files (Java, properties and POM). It's basically the RAG application with some additional tests.
2. To make sure the Postgres database is running in tests, we added the `spring.docker.compose.skip.in-tests=false` property to the `application.properties` file.
3. The `RelevancyEvaluator` is a simple implementation by the Spring AI team that calls the AI again to check for relevance. See the source code!
4. `CorrectnessEvaluator` is another custom implementation by Craig Walls, the author of Spring in Action. See the source code!
5. The [Correctness evaluator #969](https://github.com/spring-projects/spring-ai/pull/969) PR is still open, can you see the difference?
