package com.fortytwotalents.openai.spring.ai;

import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.memory.MessageWindowChatMemory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@Slf4j
@SpringBootApplication
public class OpenAiSessionSpringAiApplication {

	public static void main(String[] args) {
		SpringApplication.run(OpenAiSessionSpringAiApplication.class, args);
	}

	@Bean
	ChatClient chatClient(ChatClient.Builder builder) {
		return builder.build();
	}

	@Bean
	CommandLineRunner commandLineRunner(ChatClient chatClient) {
		return args -> {

			ChatMemory chatMemory = MessageWindowChatMemory.builder().build();
			MessageChatMemoryAdvisor chatMemoryAdvisor = MessageChatMemoryAdvisor.builder(chatMemory).build();

			String answer1 = chatClient.prompt()
				.user("Hi, my name is Patrick. Can you please list the cast of Star Trek: The Next Generation (TNG)?")
				.advisors(chatMemoryAdvisor)
				.call()
				.content();

			log.info("Answer 1: {}", answer1);

			String answer2 = chatClient.prompt()
				.user("Which character has the same name as me?")
				.advisors(chatMemoryAdvisor)
				.call()
				.content();

			log.info("Answer 2: {}", answer2);
		};
	}

}
