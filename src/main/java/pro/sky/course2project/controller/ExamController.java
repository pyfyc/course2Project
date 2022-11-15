package pro.sky.course2project.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    @GetMapping(path = "/get")
    public Object getQuestions(@RequestParam(value = "amount") int amount) {
        Collection<Question> randomQuestions = null;
        try {
            randomQuestions = examinerService.getQuestions(amount);
        } catch (Throwable e) {
            return e.getMessage();
        }
        return randomQuestions;
    }
}
