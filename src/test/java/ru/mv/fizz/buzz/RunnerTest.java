package ru.mv.fizz.buzz;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;

class RunnerTest {

    /**
     * in/out streams to swap System in out
     */
    private ByteArrayOutputStream outContent;
    private ByteArrayInputStream inContent;

    /**
     * original in/out streams links
     */
    private static final PrintStream originalOut = System.out;
    private static final InputStream originalIn = System.in;

    @BeforeEach
    public void setUpStreams() {
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    void restoreStreams() {
        System.setOut(originalOut);
        System.setIn(originalIn);
    }

    @DisplayName("Тестирование на вывод: \"Число: Fizz!\"")
    @ParameterizedTest
    @ValueSource(strings = {"9", "6", "12", "21"})
    void main_whenOutputFizz(String param) {
        provideInput(param);
        assertTimeoutPreemptively(
                Duration.ofSeconds(10),
                () -> Runner.main(new String[0]));
        Assertions.assertEquals("Число: Fizz!", outContent.toString().split("\n")[3]);
    }

    @DisplayName("Тестирование на вывод: \"Число: Buzz!\"")
    @ParameterizedTest
    @ValueSource(strings = {"5", "10", "20", "25"})
    void main_whenOutputBuzz(String param) {
        provideInput(param);
        assertTimeoutPreemptively(
                Duration.ofSeconds(10),
                () -> Runner.main(new String[0]));
        Assertions.assertEquals("Число: Buzz!", outContent.toString().split("\n")[3]);
    }

    @DisplayName("Тестирование на вывод: \"Число: FizzBuzz!\"")
    @ParameterizedTest
    @ValueSource(strings = {"15", "30", "45", "75"})
    void main_whenOutputFizzBuzz(String param) {
        provideInput(param);
        assertTimeoutPreemptively(
                Duration.ofSeconds(10),
                () -> Runner.main(new String[0]));
        Assertions.assertEquals("Число: FizzBuzz!", outContent.toString().split("\n")[3]);
    }

    @DisplayName("Тестирование на вывод: \"Число: Это не число!\"")
    @ParameterizedTest
    @ValueSource(strings = {"wrong", "string", "ever", "night"})
    void main_whenNeedRepeat(String param) {
        provideInput(param);
        assertTimeoutPreemptively(
                Duration.ofSeconds(10),
                () -> Runner.main(new String[0]));
        Assertions.assertEquals("Это не число!", outContent.toString().split("\n")[3]);
    }

    @DisplayName("Тестирование на вывод: \"Число: плохое число\"")
    @ParameterizedTest
    @ValueSource(strings = {"7", "8", "11", "13"})
    void main_whenWrongNumber(String param) {
        provideInput(param);
        assertTimeoutPreemptively(
                Duration.ofSeconds(10),
                () -> Runner.main(new String[0]));
        Assertions.assertEquals(String.format("Число: %s", param), outContent.toString().split("\n")[3]);
    }

    /**
     * Change default System.in and fill with strings to imitate user input
     *
     * @param data - string for application input
     */
    private void provideInput(String data) {
        inContent = new ByteArrayInputStream(data.getBytes());
        System.setIn(inContent);
    }
}