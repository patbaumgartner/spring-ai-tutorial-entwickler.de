package com.fortytwotalents.openai.spring.request;

import java.util.List;

public record OpenAiRequest(String model, List<Message> messages) {
}
