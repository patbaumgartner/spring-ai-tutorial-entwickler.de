package com.fortytwotalents.openai.spring.response;

public record Message(String role, String content, String refusal) {
}
