import java.util.List;
import java.util.Optional;

static class OddProductCalculator {

    static Optional<Integer> calculateOddProduct(List<Integer> numbers) {
        return numbers.stream()
                .filter(n -> n % 2 != 0)
                .reduce((a, b) -> a * b);
    }
}

void main() {
    List<Integer> mixedNumbers = List.of(1, 2, 3, 4, 5);
    Optional<Integer> result1 = OddProductCalculator.calculateOddProduct(mixedNumbers);

    System.out.println("Добуток непарних (Тест 1): " + result1.orElse(0));

    List<Integer> onlyEvens = List.of(2, 4, 6, 8);
    Optional<Integer> result2 = OddProductCalculator.calculateOddProduct(onlyEvens);

    if (result2.isEmpty()) {
        System.out.println("Тест 2: Непарних чисел немає, результат порожній.");
    }
}