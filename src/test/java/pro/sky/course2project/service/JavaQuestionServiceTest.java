package pro.sky.course2project.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pro.sky.course2project.model.Question;
import pro.sky.course2project.service.impl.JavaQuestionService;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class JavaQuestionServiceTest {
    @Mock
    private QuestionRepository questionRepositoryMock;
    @InjectMocks
    private QuestionService questionService = new JavaQuestionService(questionRepositoryMock);

    @AfterEach
    public void AfterEach() {
        questionService.getAllQuestions().forEach(question -> questionService.removeQuestion(question));
    }

    @ParameterizedTest
    @MethodSource("params")
    void addQuestionPositiveTest(String question, String answer) {
        when(questionRepositoryMock.addQuestion(question, answer))
                .thenReturn(true);
        when(questionRepositoryMock.addQuestion(new Question(question, answer)))
                .thenReturn(true);

        assertEquals(true, questionService.addQuestion(question, answer));
        assertEquals(true, questionService.addQuestion(new Question(question, answer)));

        verify(questionRepositoryMock, times(1)).addQuestion(question, answer);
        verify(questionRepositoryMock, times(1)).addQuestion(new Question(question, answer));
    }

    @ParameterizedTest
    @MethodSource("params")
    void removeQuestionPositiveTest(String question, String answer) {
        when(questionRepositoryMock.removeQuestion(new Question(question, answer)))
                .thenReturn(true);

        assertEquals(true, questionService.removeQuestion(new Question(question, answer)));

        verify(questionRepositoryMock, times(1)).removeQuestion(new Question(question, answer));
    }

    public static Stream<Arguments> params() {
        return Stream.of(
                Arguments.of("Question1", "Answer1")
        );
    }
}