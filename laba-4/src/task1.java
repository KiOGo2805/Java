import java.util.List;
import java.util.Optional;

static class StringProcessor {

    static Optional<String> findString(List<String> strings) {
        return strings.stream()
                .filter(s -> s.startsWith("X")) // 2. Залишаємо тільки ті, що починаються на "X"
                .filter(s -> s.length() > 5)    // 3. Залишаємо ті, що довші за 5 символів
                .findFirst()                    // 4. Беремо перший-ліпший, який пройшов фільтри
                .or(() -> Optional.of("Default")); // 5. Якщо нічого не пройшло, пакуємо "Default"
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