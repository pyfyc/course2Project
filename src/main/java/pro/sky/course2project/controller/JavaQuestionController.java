package pro.sky.course2project.controller;

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

    public JavaQuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @GetMapping(path = "/java/add")
    public Object addQuestion(
            @RequestParam(value = "question") String questionText,
            @RequestParam(value = "answer") String answerText) {
        Question question = null;
        try {
            question = questionService.add(questionText, answerText);
        } catch (Throwable e) {
            return e.getMessage();
        }
        return question;
    }

    @GetMapping(path = "/java/remove")
    public Object removeQuestion(
            @RequestParam(value = "question") String questionText,
            @RequestParam(value = "answer") String answerText) {
        Question question = new Question(questionText, answerText);
        try {
            question = questionService.remove(question);
        } catch (Throwable e) {
            return e.getMessage();
        }
        return question;
    }

    @GetMapping(path = "/java")
    public Object getAll() {
        Collection<Question> questions = null;
        try {
            questions = questionService.getAll();
        } catch (Throwable e) {
            return e.getMessage();
        }
        return questions;
    }

}
