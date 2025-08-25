package com.fortytwotalents.openai.spring.response;

public record Choice(long index, Message message, String logprobs, String finish_reason) {
}
