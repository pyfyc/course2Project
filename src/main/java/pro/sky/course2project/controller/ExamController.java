package pro.sky.course2project.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pro.sky.course2project.model.Question;
import pro.sky.course2project.service.ExaminerService;

import java.util.Collection;

@RestController
@RequestMapping("/exam")
public class ExamController {
    private final ExaminerService examinerService;

    public ExamController(ExaminerService examinerService) {
        this.examinerService = examinerService;
    }

    // There are 2 ways to pass parameters from frontend:
    // 1. http://localhost:8080/exam/get?amount=5
    // 2. http://localhost:8080/exam/get/5
    // Here we are using way #2.
    @GetMapping(path = "/get/{amount}")
    public Collection<Question> getQuestions(@PathVariable(value = "amount") int amount) {
        Collection<Question> randomQuestions = null;
        randomQuestions = examinerService.getQuestions(amount);
        return randomQuestions;
    }
}
