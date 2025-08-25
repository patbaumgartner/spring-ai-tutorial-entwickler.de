package com.fortytwotalents.claude.spring.ai;

import com.fortytwotalents.claude.spring.ai.code.GeneratedCodeService;
import com.fortytwotalents.claude.spring.ai.code.GeneratedCodeService.Code;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ClaudeSpringAiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClaudeSpringAiApplication.class, args);
	}

	@Bean
	ChatClient chatClient(ChatClient.Builder chatClientBuilder) {
		return chatClientBuilder.defaultSystem("""
				You are helpful AI assistant for writing code. Each class or method you are
				asked to generate should have a supporting test class to cover that method or
				methods. Please include each test in the result.

				Please generate concise and readable code geared towards beginners.
				""").build();
	}

	@Bean
	CommandLineRunner commandLineRunner(ChatClient chatClient, GeneratedCodeService codeService) {
		return args -> {

			Code code = chatClient.prompt().user("""
					Generate a Java class that contains math operations.
					Please contain more than just the basic 4 arithmetic operations.
					""").call().entity(Code.class);

			codeService.writeToFile(new String[] { code.code(), code.test() });
		};
	}

}
