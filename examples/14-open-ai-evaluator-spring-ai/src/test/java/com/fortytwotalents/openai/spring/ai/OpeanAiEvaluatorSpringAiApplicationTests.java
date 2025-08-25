package com.fortytwotalents.openai.spring.ai;

import com.fortytwotalents.openai.spring.ai.evaluation.CorrectnessEvaluationRequest;
import com.fortytwotalents.openai.spring.ai.evaluation.CorrectnessEvaluator;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.vectorstore.QuestionAnswerAdvisor;
import org.springframework.ai.chat.evaluation.RelevancyEvaluator;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.evaluation.EvaluationRequest;
import org.springframework.ai.evaluation.EvaluationResponse;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class OpeanAiEvaluatorSpringAiApplicationTests {

	@Autowired
	private ChatModel chatModel;

	@Autowired
	private VectorStore vectorStore;

	@Test
	@Disabled("Run test manually.")
	void relevancyEvaluation() {

		String userText = "Why is the definition of AI difficult?";

		ChatResponse response = ChatClient.builder(chatModel)
			.build()
			.prompt()
			.advisors(new QuestionAnswerAdvisor(vectorStore))
			.user(userText)
			.call()
			.chatResponse();

		RelevancyEvaluator relevancyEvaluator = new RelevancyEvaluator(ChatClient.builder(chatModel));
		EvaluationRequest evaluationRequest = new EvaluationRequest(userText,
				response.getMetadata().get(QuestionAnswerAdvisor.RETRIEVED_DOCUMENTS),
				response.getResult().getOutput().getText());
		EvaluationResponse evaluationResponse = relevancyEvaluator.evaluate(evaluationRequest);

		assertTrue(evaluationResponse.isPass(), "Response is not relevant to the question");
	}

	@Test
	@Disabled("Run test manually.")
	void correctnessEvaluation() {

		String userText = "Why is the definition of AI difficult?";

		ChatResponse response = ChatClient.builder(chatModel)
			.build()
			.prompt()
			.advisors(new QuestionAnswerAdvisor(vectorStore))
			.user(userText)
			.call()
			.chatResponse();

		CorrectnessEvaluator correctnessEvaluator = new CorrectnessEvaluator(ChatClient.builder(chatModel), 3.5f);
		CorrectnessEvaluationRequest evaluationRequest = new CorrectnessEvaluationRequest(userText, List.of(),
				response.getResult().getOutput().getText(), "It's a wide field.");
		EvaluationResponse evaluationResponse = correctnessEvaluator.evaluate(evaluationRequest);

		assertTrue(evaluationResponse.isPass(), "Response is not relevant to the question");
	}

}