import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;

public class TransactionAnalyzerTest {

    @Test
    public void testCalculateTotalBalance() {
        Transaction t1 = new Transaction("01-01-2023", 100.0, "Дохід");
        Transaction t2 = new Transaction("02-01-2023", -50.0, "Витрата");
        List<Transaction> list = Arrays.asList(t1, t2);

        double result = TransactionAnalyzer.calculateTotalBalance(list);
        Assertions.assertEquals(50.0, result, "Розрахунок загального балансу неправильний");
    }

    @Test
    public void testFindTopExpenses() {
        Transaction t1 = new Transaction("01-01-2023", -5000.0, "Аренда");
        Transaction t2 = new Transaction("02-01-2023", 10000.0, "Зарплата"); // не трата
        Transaction t3 = new Transaction("03-01-2023", -100.0, "Кофе");

        List<Transaction> list = Arrays.asList(t1, t2, t3);
        List<Transaction> top = TransactionAnalyzer.findTopExpenses(list);

        Assertions.assertEquals(2, top.size(), "Повинні бути лише від'ємні значення");
        Assertions.assertEquals(-5000.0, top.get(0).getAmount(), "Найбільша витрата має бути першою");
    }

    @Test
    public void testReadDataFromCSV() {
        String url = "https://informer.com.ua/dut/java/pr2.csv";
        List<Transaction> list = TransactionCSVReader.readTransactions(url);

        Assertions.assertFalse(list.isEmpty(), "Список транзакцій не повинен бути порожнім");
    }
}