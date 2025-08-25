package com.fortytwotalents.openai.spring.response;

public record Usage(long prompt_tokens, long completion_tokens, long total_tokens) {
}
