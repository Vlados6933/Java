public class TestWordAutomaton {

    public String processString(String input) {
        String currentState = "S";

        if (input == null || input.isEmpty()) {
            return currentState;
        }

        for (char ch : input.toCharArray()) {
            currentState = switch (currentState) {
                case "S" -> (ch == 'T') ? "1" : "S";

                case "1" -> switch (ch) {
                    case 'E' -> "2";
                    case 'T' -> "1"; // Зірочка: якщо після T йде T (наприклад "TT"), залишаємось в 1, а не падаємо в S!
                    default -> "S";
                };

                case "2" -> switch (ch) {
                    case 'S' -> "3";
                    case 'T' -> "1"; // Зірочка: якщо після TE йде T (наприклад "TET"), переходимо в 1, бо ця T може бути початком нового "TEST"
                    default -> "S";
                };

                case "3" -> (ch == 'T') ? "F" : "S";

                case "F" -> "F"; // Стан F кінцевий, залишаємось у ньому до кінця рядка

                default -> throw new IllegalStateException("Невідомий стан автомата: " + currentState);
            };
        }

        return currentState;
    }
}