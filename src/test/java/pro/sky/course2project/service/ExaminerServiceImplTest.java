package pro.sky.course2project.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pro.sky.course2project.exception.NotEnoughQuestionsException;
import pro.sky.course2project.model.Question;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ExaminerServiceImplTest {

    @Mock
    private QuestionService questionService;

    @InjectMocks
    private ExaminerServiceImpl examinerService;

    @BeforeEach
    public void beforeEach() {
        List<Question> questions = List.of(
                new Question("Question1", "Answer1"),
                new Question("Question2", "Answer2"),
                new Question("Question3", "Answer3"),
                new Question("Question4", "Answer4"),
                new Question("Question5", "Answer5"),
                new Question("Question6", "Answer6"),
                new Question("Question7", "Answer7"),
                new Question("Question8", "Answer8"),
                new Question("Question9", "Answer9"),
                new Question("Question10", "Answer10")
        );
        when(questionService.getAll()).thenReturn(questions);
    }

    @Test
    void getQuestionsPositiveTest() {
        // Not working. Calling getQuestions() goes into infinite loop.
        assertThat(examinerService.getQuestions(3))
                .hasSize(3);
    }

    @Test
    void getQuestionsNegativeTest() {
        assertThatExceptionOfType(NotEnoughQuestionsException.class)
                .isThrownBy(() -> examinerService.getQuestions(11));
    }
}