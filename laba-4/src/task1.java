import java.util.List;
import java.util.Optional;

static class StringProcessor {

    static Optional<String> findString(List<String> strings) {
        return strings.stream()
                .filter(s -> s.startsWith("X"))
                .filter(s -> s.length() > 5)
                .findFirst()
                .or(() -> Optional.of("Default"));
    }
}

void main() {
    List<String> list1 = List.of("Apple", "Xray", "Xylophone", "Xenomorph", "Cat");
    System.out.println("Тест 1: " + StringProcessor.findString(list1).get());

    List<String> list2 = List.of("Apple", "Xbox", "Xmen", "Dog");
    System.out.println("Тест 2: " + StringProcessor.findString(list2).get());

    List<String> list3 = List.of("Apple", "Banana", "Cherry");
    System.out.println("Тест 3: " + StringProcessor.findString(list3).get());
}