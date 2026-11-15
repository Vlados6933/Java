import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class TestWordAutomatonTest {

    private TestWordAutomaton automaton;

    @BeforeEach
    public void setUp() {
        automaton = new TestWordAutomaton();
    }

    // 1. Базові тести з умови завдання
    @ParameterizedTest(name = "Рядок: ''{0}'' має повертати стан: {1}")
    @CsvSource({
            "abcTESTabc, F",
            "abcTES, 3",
            "T, 1",
            "TE, 2",
            "abc, S",
            "'', S"
    })
    public void testBasicFunctionality(String input, String expectedState) {
        String result = automaton.processString(input == null ? "" : input);
        Assertions.assertEquals(expectedState, result);
    }

    // 2. Тести для "Завдання із зірочкою" (Вразливості оригінального графа)
    // Оригінальний граф падав би в стан S на цих рядках, але наш виправлений автомат повертає F
    @ParameterizedTest(name = "Вразливий рядок (зірочка): ''{0}'' має повертати стан: {1}")
    @CsvSource({
            "TTEST, F",    // Якщо після T йде ще одна T
            "TETEST, F",   // Якщо після TE йде T
            "TESTEST, F",  // Перевірка накладання
            "TTTTEST, F"   // Багато букв T перед EST
    })
    public void testAsteriskVulnerabilities(String input, String expectedState) {
        String result = automaton.processString(input);
        Assertions.assertEquals(expectedState, result,
                "Автомат не впорався з вразливим рядком. Логіку відкату станів порушено!");
    }
}