package com.cloud.testing;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

import java.util.Map;

@AllArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class TestController {


    private final TestService testService;


    @PostMapping("/")
    public Flux<TestQuestionDto> getTest(@RequestBody Map<String,String> testType) {
        return testService.getTest(testType.get("testType"));
    }
}
