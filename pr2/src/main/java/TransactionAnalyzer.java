import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public abstract class TransactionAnalyzer {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    public static double calculateTotalBalance(List<Transaction> transactions) {
        return transactions.stream().mapToDouble(Transaction::getAmount).sum();
    }

    public static int countTransactionsByMonth(List<Transaction> transactions, String monthYear) {
        int count = 0;
        for (Transaction t : transactions) {
            LocalDate date = LocalDate.parse(t.getDate(), FORMATTER);
            if (date.format(DateTimeFormatter.ofPattern("MM-yyyy")).equals(monthYear)) {
                count++;
            }
        }
        return count;
    }

    public static List<Transaction> findTopExpenses(List<Transaction> transactions) {
        return transactions.stream()
                .filter(t -> t.getAmount() < 0) // Только траты
                .sorted(Comparator.comparing(Transaction::getAmount)) // Самые маленькие числа (самые большие траты) идут первыми
                .limit(10)
                .collect(Collectors.toList());
    }

    public static Transaction findMaxExpenseInPeriod(List<Transaction> transactions, String startDateStr, String endDateStr) {
        LocalDate start = LocalDate.parse(startDateStr, FORMATTER);
        LocalDate end = LocalDate.parse(endDateStr, FORMATTER);

        return transactions.stream()
                .filter(t -> t.getAmount() < 0)
                .filter(t -> {
                    LocalDate d = LocalDate.parse(t.getDate(), FORMATTER);
                    return !d.isBefore(start) && !d.isAfter(end);
                })
                .min(Comparator.comparing(Transaction::getAmount))
                .orElse(null);
    }

    public static Transaction findMinExpenseInPeriod(List<Transaction> transactions, String startDateStr, String endDateStr) {
        LocalDate start = LocalDate.parse(startDateStr, FORMATTER);
        LocalDate end = LocalDate.parse(endDateStr, FORMATTER);

        return transactions.stream()
                .filter(t -> t.getAmount() < 0)
                .filter(t -> {
                    LocalDate d = LocalDate.parse(t.getDate(), FORMATTER);
                    return !d.isBefore(start) && !d.isAfter(end);
                })
                .max(Comparator.comparing(Transaction::getAmount))
                .orElse(null);
    }
}