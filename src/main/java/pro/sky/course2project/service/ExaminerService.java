package pro.sky.course2project.service;

import pro.sky.course2project.model.Question;

import java.util.Collection;

public interface ExaminerService {
    Collection<Question> getQuestions(int amount);
}
