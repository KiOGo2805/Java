import java.util.List;
import java.util.Optional;

static class NumberExtractor {

    static List<Integer> extractPresentValues(List<Optional<Integer>> optionalsList) {
        return optionalsList.stream()
                .filter(Optional::isPresent)
                .map(Optional::get)
                .toList();
    }
}

void main() {
    List<Optional<Integer>> mixedList = List.of(
            Optional.of(1),
            Optional.empty(),
            Optional.of(2),
            Optional.empty(),
            Optional.of(33)
    );

    System.out.println("Початковий список (з коробками): " + mixedList);

    List<Integer> cleanNumbers = NumberExtractor.extractPresentValues(mixedList);

    System.out.println("Очищений список (тільки числа): " + cleanNumbers);
}