package com.fortytwotalents.openai.spring.ai.pgvector;

import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.SafeGuardAdvisor;
import org.springframework.ai.rag.advisor.RetrievalAugmentationAdvisor;
import org.springframework.ai.rag.retrieval.search.VectorStoreDocumentRetriever;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;

import java.util.List;

@Slf4j
@SpringBootApplication
public class OpenAiGuardrailsSpringAiApplication {

	@Value("Artificial intelligence - Wikipedia.pdf")
	private Resource pdf;

	public static void main(String[] args) {
		SpringApplication.run(OpenAiGuardrailsSpringAiApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(ChatClient.Builder chatClientBuilder, VectorStore vectorStore) {
		ChatClient chatClient = chatClientBuilder.build();

		return args -> {
			RetrievalAugmentationAdvisor retrievalAugmentationAdvisor = RetrievalAugmentationAdvisor.builder()
				.documentRetriever(VectorStoreDocumentRetriever.builder()
					.similarityThreshold(0.50)
					.vectorStore(vectorStore)
					.build())
				.build();

			SafeGuardAdvisor safeguardAdvisor = SafeGuardAdvisor.builder()
				.sensitiveWords(List.of("confidential", "secret", "internal", "proprietary", "classified"))
				.build();

			String answer = chatClient.prompt()
				.system("""
						You are a virtual assistant and answers questions with the data provided.
						If you are not sure or don't know, honestly say you don't know.
						""")
				.advisors(retrievalAugmentationAdvisor, safeguardAdvisor)
				.user("Why is the definition of AI difficult? Is there something confidential about it?")
				.call()
				.content();

			log.info("ChatGPT answered: {}", answer);
		};
	}

}
