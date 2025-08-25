package com.fortytwotalents.openai.spring.ai;

import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@SpringBootApplication
public class OpenAiObservabilitySpringAiApplication {

	public static void main(String[] args) {
		SpringApplication.run(OpenAiObservabilitySpringAiApplication.class, args);
	}

	@Bean
	ChatClient chatClient(ChatClient.Builder builder) {
		return builder.build();
	}

	@RestController
	@RequiredArgsConstructor
	static class JokeController {

		private final ChatClient chatClient;

		@GetMapping("/joke")
		Map<String, String> joke() {

			String reply = chatClient.prompt().user("""
					Tell me a joke. Be concise. Don't send anything except the joke.
					""").call().content();

			return Map.of("joke", reply);
		}

	}

}
