package com.patbaumgartner.mcp.client.weather;

import io.modelcontextprotocol.client.McpSyncClient;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.chat.memory.MessageWindowChatMemory;
import org.springframework.ai.mcp.SyncMcpToolCallbackProvider;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@SpringBootApplication
public class McpClientWeatherStdioApplication {

	public static void main(String[] args) {
		SpringApplication.run(McpClientWeatherStdioApplication.class, args);
	}

	@RestController
	static class WeatherController {

		private final ChatClient chatClient;

		public WeatherController(ChatClient.Builder chatClientBuilder, List<McpSyncClient> mcpSyncClients) {
			this.chatClient = chatClientBuilder
					.defaultSystem(
							"""
									You are a weather data analysis assistant. You receive structured forecast data from various weather stations.

									Data units:
									- Temperature in 0.1 °C
									- Start time Unix time (ms)
									- Time interval (ms)
									- Total precipitation in 0.1 mm/h
									- Daily precipitation in 0.1 mm/d
									- Sunshine in 0.1 min
									- Humidity in 0.1 %
									- Dew point in 0.1 °C (2 m height)
									- Surface pressure in 0.1 hPa (ground level)

									Instructions:
									- Convert all values into readable units.
									- Detect and describe trends.
									- Comment on missing values (null).
									- Summarize temperature, precipitation, sunshine, humidity, dew point, wind (if available), and surface pressure separately.
									- Finally, create a brief weather summary (weather conditions, temperature trend, comfort indicators, special events).

									Always explain your reasoning clearly, step-by-step, and in complete sentences. Keep answers friendly, concise, and insightful.
									""")
					.defaultToolCallbacks(new SyncMcpToolCallbackProvider(mcpSyncClients))
					.defaultAdvisors(
							MessageChatMemoryAdvisor.builder(MessageWindowChatMemory.builder().build()).build(),
							new SimpleLoggerAdvisor())
					.build();
		}

		@PostMapping("/ask")
		public Answer ask(@RequestBody Question question) {
			return chatClient.prompt().user(question.question()).call().entity(Answer.class);
		}

		public record Question(String question) {
		}

		public record Answer(String answer) {
		}
	}
}
