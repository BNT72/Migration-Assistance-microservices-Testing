package com.cloud.testing;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Component
public class MongoMigration implements CommandLineRunner {

    @Value("${mongo.migration}")
    private String migrationFilePath;

    private final QuestionRepository questionRepository;


    public MongoMigration(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }


    @Override
    public void run(String... args) {
        ObjectMapper objectMapper = new ObjectMapper();
        testMigration(objectMapper);

    }

    private void testMigration(ObjectMapper objectMapper) {
        List<TestQuestionEntity> testQuestionEntities;
        try {
            testQuestionEntities = objectMapper.readValue(new File(migrationFilePath), new TypeReference<>() {
            });

            Flux.fromIterable(testQuestionEntities).flatMap(testQuestionEntity ->
                            questionRepository.findByQuestion(testQuestionEntity.getQuestion())
                                    .map(testQuestionEntity1 -> testQuestionEntity1).switchIfEmpty(questionRepository.save(testQuestionEntity))
                    )
                    .subscribe();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
