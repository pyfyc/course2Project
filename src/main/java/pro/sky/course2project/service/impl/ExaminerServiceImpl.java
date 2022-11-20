package pro.sky.course2project.service.impl;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import pro.sky.course2project.exception.NotEnoughQuestionsException;
import pro.sky.course2project.model.Question;
import pro.sky.course2project.service.ExaminerService;
import pro.sky.course2project.service.QuestionService;

import java.util.Collection;
import java.util.HashSet;
import java.util.Random;

@Service
public class ExaminerServiceImpl implements ExaminerService {
    private Random random;
    private QuestionService javaQuestionService;
    private QuestionService mathQuestionService;
    private Collection<Question> randomQuestions = new HashSet<>();

    public ExaminerServiceImpl(
            @Qualifier("javaQuestionService") QuestionService javaQuestionService,
            @Qualifier("mathQuestionService") QuestionService mathQuestionService) {
        this.javaQuestionService = javaQuestionService;
        this.mathQuestionService = mathQuestionService;
        this.random = new Random();
    }

    @Override
    public Collection<Question> getQuestions(int amount) {
        if (amount > javaQuestionService.getAllQuestions().size() + mathQuestionService.getAllQuestions().size()) {
            throw new NotEnoughQuestionsException("Not enough questions");
        }
        randomQuestions.clear();
        while (randomQuestions.size() < amount) {
            int questionCategory = random.nextInt(2);
            switch (questionCategory) {
                case 0: // add java question
                    Question q1 = javaQuestionService.getRandomQuestion();
                    randomQuestions.add(q1);
                    break;
                case 1: // add math question
                    Question q2 = mathQuestionService.getRandomQuestion();
                    randomQuestions.add(q2);
                    break;
                default:
                    throw new RuntimeException("Invalid questionCategory value: " + questionCategory);
            }
        }
        return randomQuestions;
    }
}
