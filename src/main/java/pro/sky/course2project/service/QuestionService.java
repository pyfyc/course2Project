package pro.sky.course2project.service;

import pro.sky.course2project.model.Question;

import java.util.Collection;

public interface QuestionService {
    Boolean addQuestion(String question, String answer);

    Boolean addQuestion(Question question);

    Boolean removeQuestion(Question question);

    Collection<Question> getAllQuestions();

    Question getRandomQuestion();
}
