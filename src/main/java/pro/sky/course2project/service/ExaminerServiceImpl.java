package pro.sky.course2project.service;

import org.springframework.stereotype.Service;
import pro.sky.course2project.exception.NotEnoughQuestionsException;
import pro.sky.course2project.model.Question;

import java.util.Collection;
import java.util.HashSet;
import java.util.Random;

@Service
public class ExaminerServiceImpl implements ExaminerService {
    private Random random;
    private QuestionService questionService;
    private Collection<Question> randomQuestions = new HashSet<>();

    public ExaminerServiceImpl(QuestionService questionService) {
        this.questionService = questionService;
    }

    @Override
    public Collection<Question> getQuestions(int amount) {
        if (amount > questionService.getAll().size()) {
            throw new NotEnoughQuestionsException("Not enough questions");
        }
        randomQuestions.clear();
        while (randomQuestions.size() < amount) {
            randomQuestions.add(questionService.getRandomQuestion());
        }
        return randomQuestions;
    }
}
