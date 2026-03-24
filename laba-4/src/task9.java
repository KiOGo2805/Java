import java.util.List;
import java.util.Map;
import java.util.Optional;

static class ProductNameFormatter {

    static List<String> getUppercaseNames(Map<Integer, Optional<String>> productsMap) {

        return productsMap.values().stream()
                .filter(Optional::isPresent)
                .map(Optional::get)
                .map(String::toUpperCase)
                .toList();
    }
}

void main() {
    Map<Integer, Optional<String>> inventory = Map.of(
            101, Optional.of("Ноутбук"),
            102, Optional.empty(),
            103, Optional.of("Клавіатура"),
            104, Optional.empty(),
            105, Optional.of("Монітор")
    );

    List<String> uppercaseNames = ProductNameFormatter.getUppercaseNames(inventory);

    System.out.println("Оброблений список товарів:");
    System.out.println(uppercaseNames);
}