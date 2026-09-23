import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public abstract class TransactionReportGenerator {

    public static void printBalanceReport(double totalBalance) {
        System.out.println("Загальний баланс: " + totalBalance);
    }

    public static void printTransactionsCountByMonth(String monthYear, int count) {
        System.out.println("Кількість транзакцій за " + monthYear + ": " + count);
    }

    public static void printTopExpensesReport(List<Transaction> topExpenses) {
        System.out.println("\n--- 10 найбільших витрат ---");
        for (Transaction expense : topExpenses) {
            System.out.println(expense.getDescription() + ": " + expense.getAmount());
        }
    }

    public static void printExpenseVisualizationByCategory(List<Transaction> transactions) {
        System.out.println("\n--- Звіт по категоріях (кожна '*' = 1000 грн витрат) ---");

        Map<String, Double> expensesByCategory = transactions.stream()
                .filter(t -> t.getAmount() < 0)
                .collect(Collectors.groupingBy(
                        Transaction::getDescription,
                        Collectors.summingDouble(t -> Math.abs(t.getAmount()))
                ));

        expensesByCategory.forEach((category, sum) -> {
            int stars = (int) (sum / 1000);
            System.out.println(category + ": " + "*".repeat(Math.max(0, stars)) + " (" + sum + " грн)");
        });
    }

    public static void printExpenseVisualizationByMonth(List<Transaction> transactions) {
        System.out.println("\n--- Звіт по місяцях (кожна '*' = 1000 грн витрат) ---");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        Map<String, Double> expensesByMonth = transactions.stream()
                .filter(t -> t.getAmount() < 0)
                .collect(Collectors.groupingBy(
                        t -> LocalDate.parse(t.getDate(), formatter).format(DateTimeFormatter.ofPattern("MM-yyyy")),
                        Collectors.summingDouble(t -> Math.abs(t.getAmount()))
                ));

        expensesByMonth.forEach((month, sum) -> {
            int stars = (int) (sum / 1000);
            System.out.println(month + ": " + "*".repeat(Math.max(0, stars)) + " (" + sum + " грн)");
        });
    }
}