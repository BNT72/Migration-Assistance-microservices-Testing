package com.cloud.testing;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface QuestionRepository extends ReactiveMongoRepository<TestQuestionEntity, String> {


    Mono<TestQuestionEntity> findByQuestion(String question);

    Flux<TestQuestionEntity> findAllByType(ETestType type);
}
