package com.cloud.testing;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.util.Optional;

@AllArgsConstructor
@Component
public class TestService {
    private final QuestionRepository questionRepository;

    public Flux<TestQuestionDto> getTest(String testType) {
        Optional<ETestType> type = Optional.of(ETestType.valueOf(testType));
        return questionRepository
                .findAllByType(type.orElseThrow(IllegalArgumentException::new))
                .map(TestQuestionDto::new);
    }
}
