package com.fortytwotalents.openai.spring.ai;

import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.util.MimeTypeUtils;

@Slf4j
@SpringBootApplication
public class OpenAiMultiModelSpringAiApplication {

	public static void main(String[] args) {
		SpringApplication.run(OpenAiMultiModelSpringAiApplication.class, args);
	}

	@Bean
	ChatClient chatClient(ChatClient.Builder builder) {
		return builder.build();
	}

	@Bean
	CommandLineRunner commandLineRunner(ChatClient chatClient) {
		return args -> {
			String prompt = """
						Describe the image in detail. Focus on the following aspects:

						1. Architecture: Style, materials, and notable design features of the building(s).
						2. Foreground elements: Fountain, statue, plants, pavement, or any decorative features.
						3. Environment: Weather, lighting, and overall atmosphere.
						4. Layout: Relationship between the building, fountain, and surrounding open space.
						5. Possible use: Suggest what kind of building this could be (e.g., historical, residential, institutional) based only on visible features.

						Finally, based on visual clues, suggest in which city and country this scene is most likely located. If uncertain, provide the most plausible options with reasoning.
					""";
			Resource image = new ClassPathResource("/2025-08-17 16.06.21.jpg");

			String response = chatClient.prompt()
					.user(u -> u.text(prompt).media(MimeTypeUtils.IMAGE_JPEG, image))
					.call()
					.content();

			log.info("Response: {}", response);
		};
	}

}
