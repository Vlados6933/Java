import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CalculatorTest {

    private Calculator calculator;

    @BeforeEach
    public void setUp() {
        calculator = new Calculator();
    }

    @Test
    public void testDivideByZeroThrowsException() {
        // Перевірка ділення на нуль
        Assertions.assertThrows(ArithmeticException.class, () -> {
            calculator.divide(10.0, 0.0);
        }, "Ділення на нуль має викидати ArithmeticException");
    }

    @Test
    public void testNegativeSquareRootThrowsCustomException() {
        // Перевірка випадку, що викликає власне виключення
        Assertions.assertThrows(InvalidInputException.class, () -> {
            calculator.sqrt(-25.0);
        }, "Корінь з від'ємного числа має викидати InvalidInputException");
    }

    @Test
    public void testStringParsingException() {
        // Перевірка ситуації з невірними вхідними даними (рядки замість чисел)
        String invalidInput = "abc";
        Assertions.assertThrows(NumberFormatException.class, () -> {
            Double.parseDouble(invalidInput);
        }, "Парсинг рядка з літерами має викидати NumberFormatException");
    }

    @Test
    public void testValidDivision() {
        Assertions.assertEquals(5.0, calculator.divide(10.0, 2.0));
    }
}