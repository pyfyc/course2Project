package pro.sky.course2project.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import pro.sky.course2project.model.Question;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class JavaQuestionServiceTest {
    private final QuestionService out = new JavaQuestionService();

    @AfterEach
    public void AfterEach() {
        out.getAll().forEach(question -> out.remove(question));
    }

    @ParameterizedTest
    @MethodSource("params")
    void addPositiveTest(String question, String answer) {
        assertThat(out.getAll()).isEmpty();

        Question expected = out.add(new Question(question, answer));

        assertThat(out.getAll())
                .hasSize(1)
                .containsExactly(expected);

        // Adding already existing question should return null.
        assertThat(out.add(question, answer)).isEqualTo(null);
    }

    @ParameterizedTest
    @MethodSource("params")
    void removePositiveTest(String question, String answer) {
        assertThat(out.getAll()).isEmpty();

        out.add(new Question(question, answer));
        Question expected = out.remove(new Question(question, answer));

        assertThat(expected).isEqualTo(new Question(question, answer));
        // Removing not existing question should return null.
        assertThat(out.remove(new Question(question, answer))).isEqualTo(null);
    }

    @Test
    void getRandomQuestion() {
        // How to test this?
    }

    public static Stream<Arguments> params() {
        return Stream.of(
                Arguments.of("Question1", "Answer1")
        );
    }
}