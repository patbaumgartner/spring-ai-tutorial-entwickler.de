package com.fortytwotalents.openai.java;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Slf4j
public class OpenAiJavaApplication {

	public static void main(String[] args) throws IOException, InterruptedException {
		var apiKey = System.getenv("OPENAI_API_KEY");
		var requestBody = """
				{
					"model": "gpt-5-nano",
					"messages": [
						{
						"role": "system",
						"content": "You are a friendly chatbot and you like to place emojis everywhere."
						},{
						"role": "user",
						"content": "Tell me a developer joke about Python."
						}
					]
				}""";

		var request = HttpRequest.newBuilder()
			.uri(URI.create("https://api.openai.com/v1/chat/completions"))
			.header("Content-Type", "application/json")
			.header("Authorization", "Bearer %s".formatted(apiKey))
			.POST(HttpRequest.BodyPublishers.ofString(requestBody))
			.build();

		try (var client = HttpClient.newHttpClient()) {
			HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
			if (log.isInfoEnabled()) {
				log.info(response.body());
			}
		}
	}

}
