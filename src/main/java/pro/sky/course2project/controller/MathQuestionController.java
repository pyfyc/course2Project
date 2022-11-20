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
public class MathQuestionController {
    private final QuestionService questionService;

    public MathQuestionController(@Qualifier("mathQuestionService") QuestionService questionService) {
        this.questionService = questionService;
    }

    @GetMapping(path = "/java/addMath")
    public Boolean addQuestion(
            @RequestParam(value = "question") String question,
            @RequestParam(value = "answer") String answer) {
        return questionService.addQuestion(question, answer);
    }

    @GetMapping(path = "/java/removeMath")
    public Boolean removeQuestion(
            @RequestParam(value = "question") String question,
            @RequestParam(value = "answer") String answer) {
        return questionService.removeQuestion(new Question(question, answer));
    }

    @GetMapping(path = "/javaMath")
    public Object getAll() {
        Collection<Question> questions = null;
        try {
            questions = questionService.getAllQuestions();
        } catch (Throwable e) {
            return e.getMessage();
        }
        return questions;
    }
}
