import java.util.List;

public class Main {
    public static void main(String[] args) {
        String filePath = "https://informer.com.ua/dut/java/pr2.csv";

        List<Transaction> transactions = TransactionCSVReader.readTransactions(filePath);

        double totalBalance = TransactionAnalyzer.calculateTotalBalance(transactions);
        TransactionReportGenerator.printBalanceReport(totalBalance);

        String monthYear = "12-2023";
        int count = TransactionAnalyzer.countTransactionsByMonth(transactions, monthYear);
        TransactionReportGenerator.printTransactionsCountByMonth(monthYear, count);

        List<Transaction> topExpenses = TransactionAnalyzer.findTopExpenses(transactions);
        TransactionReportGenerator.printTopExpensesReport(topExpenses);

        System.out.println("\n--- Найбільша та найменша витрати за період 01-12-2023 по 31-12-2023 ---");
        Transaction maxExp = TransactionAnalyzer.findMaxExpenseInPeriod(transactions, "01-12-2023", "31-12-2023");
        Transaction minExp = TransactionAnalyzer.findMinExpenseInPeriod(transactions, "01-12-2023", "31-12-2023");
        System.out.println("Найбільша витрата: " + (maxExp != null ? maxExp.getDescription() + " (" + maxExp.getAmount() + ")" : "Немає"));
        System.out.println("Найменша витрата: " + (minExp != null ? minExp.getDescription() + " (" + minExp.getAmount() + ")" : "Немає"));

        TransactionReportGenerator.printExpenseVisualizationByCategory(transactions);
        TransactionReportGenerator.printExpenseVisualizationByMonth(transactions);
    }
}