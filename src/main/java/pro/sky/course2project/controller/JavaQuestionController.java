package pro.sky.course2project.controller;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pro.sky.course2project.model.Question;
import pro.sky.course2project.service.QuestionService;

import java.util.Collection;

@RestController
@RequestMapping("/exam")
public class JavaQuestionController {
    private final QuestionService questionService;

    public JavaQuestionController(@Qualifier("javaQuestionService") QuestionService questionService) {
        this.questionService = questionService;
    }

    @GetMapping(path = "/java/addJava")
    public Boolean addQuestion(
            @RequestParam(value = "question") String question,
            @RequestParam(value = "answer") String answer) {
        return questionService.addQuestion(question, answer);
    }

    @GetMapping(path = "/java/removeJava")
    public Boolean removeQuestion(
            @RequestParam(value = "question") String question,
            @RequestParam(value = "answer") String answer) {
        return questionService.removeQuestion(new Question(question, answer));
    }

    @GetMapping(path = "/javaJava")
    public Collection<Question> getAll() {
        Collection<Question> questions = null;
        questions = questionService.getAllQuestions();
        return questions;
    }
}
