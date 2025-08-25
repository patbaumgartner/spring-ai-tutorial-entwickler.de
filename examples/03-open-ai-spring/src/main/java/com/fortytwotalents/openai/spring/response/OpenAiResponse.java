package com.fortytwotalents.openai.spring.response;

import java.util.List;

public record OpenAiResponse(String id, String object, long created, String model, List<Choice> choices, Usage usage,
		String system_fingerprint) {
}
