package pro.sky.course2project.service.impl;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import pro.sky.course2project.model.Question;
import pro.sky.course2project.service.QuestionRepository;
import pro.sky.course2project.service.QuestionService;

import java.util.Collection;

@Service
public class MathQuestionService implements QuestionService {
    private QuestionRepository questionRepository;

    public MathQuestionService(@Qualifier("mathQuestionRepository") QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    @Override
    public Boolean addQuestion(String question, String answer) {
        return questionRepository.addQuestion(question, answer);
    }

    @Override
    public Boolean addQuestion(Question question) {
        return questionRepository.addQuestion(question);
    }

    @Override
    public Boolean removeQuestion(Question question) {
        return questionRepository.removeQuestion(question);
    }

    @Override
    public Collection<Question> getAllQuestions() {
        return questionRepository.getAllQuestions();
    }

    @Override
    public Question getRandomQuestion() {
        return questionRepository.getRandomQuestion();
    }
}
