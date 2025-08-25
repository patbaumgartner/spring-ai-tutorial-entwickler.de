package com.fortytwotalents.openai.spring.ai.model;

import com.fortytwotalents.openai.spring.ai.service.WeatherService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;
import java.util.function.Function;

@Slf4j
@SpringBootApplication
public class OpenAiFunctionSpringAiChatModelApplication {

	public static void main(String[] args) {
		SpringApplication.run(OpenAiFunctionSpringAiChatModelApplication.class, args);
	}

	@Bean
	Function<WeatherService.Request, WeatherService.Response> weatherFunction() {
		return new WeatherService();
	}

	@Bean
	CommandLineRunner commandLineRunner(ChatModel chatModel) {

		return args -> {
			UserMessage userMessage = new UserMessage("What's the weather like in Munich, Zurich, and New York?");

			ChatResponse response = chatModel.call(
					new Prompt(List.of(userMessage), OpenAiChatOptions.builder().toolNames("weatherFunction").build()));

			String answer = response.getResult().getOutput().getText();

			log.info("ChatGPT answered: {}", answer);
		};
	}

}
