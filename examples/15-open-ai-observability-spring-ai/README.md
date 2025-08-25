# Observe Spring AI

## Observability with Spring Boot

1. The sample application uses Micrometer to expose metrics.
2. Check the actuators `/actuator/metrics` and `/actuator/metrics/{metric-name}`.
3. There should be `spring.ai` and `gen_ai` metrics.
4. Login to the Zipkin UI at `http://localhost:9411/zipkin/` and watch the traces.
5. if you set `management.tracing.sampling.probability=1.0` in `application.properties` you should see traces faster.
6. Did you notice that the provided `docker-compose.yml` started a Zipkin server? Which Spring Boot starter is responsible for that?
7. A good way to keep Zipkin running is to set `spring.docker.compose.lifecycle-management=start_only` in the `application.properties` file.
