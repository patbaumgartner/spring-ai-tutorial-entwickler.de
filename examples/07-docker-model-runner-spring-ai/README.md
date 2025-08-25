# Calling Docker Model Runner Models from Spring AI

## Exploring Docker Model Runner

1. Browse a wide selection of pre-trained models on Docker Hub at [https://hub.docker.com/u/ai](https://hub.docker.com/u/ai).
2. Docker Model Runner requires Docker Desktop version 4.40 or higher. For more details, see [https://docs.docker.com/model-runner/](https://docs.docker.com/model-runner/).
3. Spring AI supports Docker Model Runner using the existing OpenAI-backed `ChatClient`. Set the base URL to `localhost:12434/engines` and choose one of the available LLM models. See the documentation at [https://docs.spring.io/spring-ai/reference/api/chat/dmr-chat.html](https://docs.spring.io/spring-ai/reference/api/chat/dmr-chat.html).
4. To experiment locally, deploy a model using Docker Model Runner and configure it accordingly.
5. In Docker Desktop, go to Settings → Features in Development and enable Enable Docker Model Runner.
6. Pull a model using the following commands:

```bash
docker model status
docker model pull ai/gemma3:4B-F16
docker model list
```

**Note:** These commands may not work properly on Windows using WSL2. If issues occur, try running them in CMD instead.
