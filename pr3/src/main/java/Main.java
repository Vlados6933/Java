import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Calculator calculator = new Calculator();

        System.out.println("=== Калькулятор з обробкою виключень ===");
        System.out.println("Підтримувані операції: +, -, *, /, sqrt");
        System.out.println("Для виходу введіть 'exit'.");

        while (true) {
            System.out.print("\nВведіть операцію (+, -, *, /, sqrt) або 'exit': ");
            String operation = scanner.nextLine().trim();

            if (operation.equalsIgnoreCase("exit")) {
                System.out.println("Завершення роботи програми.");
                break;
            }

            try {
                System.out.print("Введіть перше число: ");
                double num1 = Double.parseDouble(scanner.nextLine().trim());

                double result = 0;

                if (operation.equals("sqrt")) {
                    result = calculator.sqrt(num1);
                    System.out.println("Результат: " + result);
                } else {
                    System.out.print("Введіть друге число: ");
                    double num2 = Double.parseDouble(scanner.nextLine().trim());

                    switch (operation) {
                        case "+":
                            result = calculator.add(num1, num2);
                            break;
                        case "-":
                            result = calculator.subtract(num1, num2);
                            break;
                        case "*":
                            result = calculator.multiply(num1, num2);
                            break;
                        case "/":
                            result = calculator.divide(num1, num2);
                            break;
                        default:
                            System.out.println("Невідома операція!");
                            continue;
                    }
                    System.out.println("Результат: " + result);
                }

            } catch (NumberFormatException e) {
                System.out.println("Критична помилка: Ви ввели рядок замість числа! Деталі: " + e.getMessage());
            } catch (ArithmeticException | InvalidInputException e) {
                System.out.println("Математична помилка: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("Невідома помилка: " + e.getMessage());
            } finally {
                System.out.println("[СИСТЕМА] Завершення обробки поточного запиту.");
            }
        }
        scanner.close();
    }
}