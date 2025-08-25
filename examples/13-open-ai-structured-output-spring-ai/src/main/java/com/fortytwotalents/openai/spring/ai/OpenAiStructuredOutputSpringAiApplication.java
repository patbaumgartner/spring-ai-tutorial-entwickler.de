package com.fortytwotalents.openai.spring.ai;

import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Map;

import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.model.Generation;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.converter.BeanOutputConverter;
import org.springframework.ai.converter.ListOutputConverter;
import org.springframework.ai.converter.MapOutputConverter;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.convert.support.DefaultConversionService;

@Slf4j
@SpringBootApplication
public class OpenAiStructuredOutputSpringAiApplication {

	public static void main(String[] args) {
		SpringApplication.run(OpenAiStructuredOutputSpringAiApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(ChatModel chatModel) {
		return args -> {
			mapOutputConverter(chatModel);
			listOutputConverter(chatModel);
			beanOutputConverter(chatModel);
		};
	}

	public void mapOutputConverter(ChatModel chatModel) {
		MapOutputConverter mapOutputConverter = new MapOutputConverter();

		String format = mapOutputConverter.getFormat();
		String subject = "an array of numbers from 1 to 9 under they key name 'numbers'";
		String template = """
				Provide me a List of {subject}
				{format}
				""";
		PromptTemplate promptTemplate = PromptTemplate.builder()
			.template(template)
			.variables(Map.of("subject", subject, "format", format))
			.build();
		Prompt prompt = promptTemplate.create();
		Generation generation = chatModel.call(prompt).getResult();

		Map<String, Object> result = mapOutputConverter.convert(generation.getOutput().getText());

		log.info("MapOutputConverter: {}", result);
	}

	public void listOutputConverter(ChatModel chatModel) {
		ListOutputConverter listOutputConverter = new ListOutputConverter(new DefaultConversionService());

		String format = listOutputConverter.getFormat();
		String subject = "ice cream flavors";
		String template = """
				List five {subject}
				{format}
				""";
		PromptTemplate promptTemplate = PromptTemplate.builder()
			.template(template)
			.variables(Map.of("subject", subject, "format", format))
			.build();
		Prompt prompt = promptTemplate.create();
		Generation generation = chatModel.call(prompt).getResult();

		List<String> list = listOutputConverter.convert(generation.getOutput().getText());
		log.info("ListOutputConverter: {}", list);
	}

	public void beanOutputConverter(ChatModel chatModel) {

		record ActorAndTheirFilms(String actor, List<String> movies) {
		}

		BeanOutputConverter<ActorAndTheirFilms> beanOutputConverter = new BeanOutputConverter<>(
				ActorAndTheirFilms.class);

		String format = beanOutputConverter.getFormat();
		String actor = "Tom Hanks";
		String template = """
				Generate the filmography of 5 movies for {actor}.
				{format}
				""";

		PromptTemplate promptTemplate = PromptTemplate.builder()
			.template(template)
			.variables(Map.of("actor", actor, "format", format))
			.build();
		Prompt prompt = promptTemplate.create();
		Generation generation = chatModel.call(prompt).getResult();

		ActorAndTheirFilms actorsFilms = beanOutputConverter.convert(generation.getOutput().getText());

		log.info("BeanOutputConverter: {}", actorsFilms);
	}

}
