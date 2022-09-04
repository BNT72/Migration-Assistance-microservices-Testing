package com.cloud.testing.repo;

import com.cloud.testing.model.TestQuestionEntity;
import com.cloud.testing.model.ETestType;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface QuestionRepository extends ReactiveMongoRepository<TestQuestionEntity, String> {


    Mono<TestQuestionEntity> findByQuestion(String question);

    Flux<TestQuestionEntity> findAllByType(ETestType type);
}
