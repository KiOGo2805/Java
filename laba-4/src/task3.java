import java.util.Comparator;
import java.util.List;
import java.util.Optional;

static class LongestNameFinder {

    static Optional<String> findLongestName(List<String> names) {
        return names.stream()
                .max(Comparator.comparingInt(String::length));
    }
}

void main() {
    List<String> namesList = List.of("Іван", "Яна", "Костянтин", "Анна");

    Optional<String> longest = LongestNameFinder.findLongestName(namesList);

    longest.ifPresent(name -> System.out.println("Найдовше ім'я: " + name));

    List<String> emptyList = List.of();
    Optional<String> emptyResult = LongestNameFinder.findLongestName(emptyList);

    if (emptyResult.isEmpty()) {
        System.out.println("Список порожній, найдовшого імені немає.");
    }
}