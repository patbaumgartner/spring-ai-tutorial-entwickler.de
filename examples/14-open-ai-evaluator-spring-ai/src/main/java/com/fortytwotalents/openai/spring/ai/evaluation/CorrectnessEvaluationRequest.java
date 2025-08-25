package com.fortytwotalents.openai.spring.ai.evaluation;

import org.springframework.ai.document.Document;
import org.springframework.ai.evaluation.EvaluationRequest;

import java.util.List;

/**
 * Represents an evaluation request for correctness evaluation.
 *
 * @author Craig Walls
 * @since 1.0.0 M2
 */
public class CorrectnessEvaluationRequest extends EvaluationRequest {

	private final String referenceAnswer;

	public CorrectnessEvaluationRequest(String userText, List<Document> dataList, String responseContent,
			String referenceAnswer) {
		super(userText, dataList, responseContent);
		this.referenceAnswer = referenceAnswer;
	}

	public String getReferenceAnswer() {
		return referenceAnswer;
	}

}