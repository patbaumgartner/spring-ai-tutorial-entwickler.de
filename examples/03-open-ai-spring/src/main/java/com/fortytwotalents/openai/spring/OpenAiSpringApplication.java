package com.fortytwotalents.openai.spring;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestTemplate;

import com.fortytwotalents.openai.spring.request.Message;
import com.fortytwotalents.openai.spring.request.OpenAiRequest;
import com.fortytwotalents.openai.spring.response.OpenAiResponse;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootApplication
public class OpenAiSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(OpenAiSpringApplication.class, args);
	}

	OpenAiRequest openAiRequest = new OpenAiRequest("gpt-5-nano",
			List.of(new Message("system", "You are a friendly chatbot and you like to place emojis everywhere."),
					new Message("user", "Tell me a developer joke about Python.")));

	@Bean
	CommandLineRunner commandLineRunnerRestTemplate(RestTemplateBuilder restTemplateBuilder,
			@Value("${openai.api-key}") String apiKey) {
		return args -> {

			RestTemplate restTemplate = restTemplateBuilder.build();
			RequestEntity<OpenAiRequest> request = RequestEntity
				.post(URI.create("https://api.openai.com/v1/chat/completions"))
				.header("Content-Type", "application/json")
				.header("Authorization", "Bearer %s".formatted(apiKey))
				.body(openAiRequest);

			ResponseEntity<OpenAiResponse> response = restTemplate.exchange(request, OpenAiResponse.class);

			if (log.isInfoEnabled()) {
				log.info(response.getBody().toString());
			}
		};
	}

	// @Bean
	CommandLineRunner commandLineRunnerRestClient(RestClient.Builder restClientBuilder,
			@Value("${openai.api-key}") String apiKey) {
		return args -> {

			OpenAiResponse response = restClientBuilder.build()
				.post()
				.uri("https://api.openai.com/v1/chat/completions")
				.header("Content-Type", "application/json")
				.header("Authorization", "Bearer %s".formatted(apiKey))
				.body(openAiRequest)
				.retrieve()
				.body(OpenAiResponse.class);

			if (log.isInfoEnabled()) {
				log.info(response.toString());
			}
		};
	}

}
