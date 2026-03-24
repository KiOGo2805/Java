import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

record Transaction(double amount, String category) {}

static class TransactionAnalyzer {

    static Map<String, Double> sumByCategory(List<Transaction> transactions) {
        return transactions.stream()
                .collect(Collectors.groupingBy(
                        Transaction::category,
                        Collectors.summingDouble(Transaction::amount)
                ));
    }
}

void main() {
    List<Transaction> history = List.of(
            new Transaction(2500, "Периферія"),
            new Transaction(25000, "Комплектуючі"),
            new Transaction(1200, "Електроніка"),
            new Transaction(3000, "Периферія"),
            new Transaction(6000, "Комплектуючі")
    );

    Map<String, Double> expensesByCategory = TransactionAnalyzer.sumByCategory(history);

    System.out.println("--- Статистика витрат ---");
    expensesByCategory.forEach((category, sum) ->
            System.out.println("Категорія '" + category + "': " + sum + " грн")
    );
}