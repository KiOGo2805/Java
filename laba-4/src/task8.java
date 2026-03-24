import java.util.Comparator;
import java.util.List;
import java.util.Optional;

record Product(String name, double price) {}

static class ProductAnalyzer {

    static Optional<String> getSecondMostExpensive(List<Product> products) {
        return products.stream()
                .sorted(Comparator.comparingDouble(Product::price).reversed())
                .skip(1)
                .findFirst()
                .map(Product::name);
    }
}

void main() {
    List<Product> store = List.of(
            new Product("Смартфон", 25000),
            new Product("Навушники", 3000),
            new Product("Ноутбук", 45000),
            new Product("Планшет", 18000)
    );

    Optional<String> secondExp = ProductAnalyzer.getSecondMostExpensive(store);
    secondExp.ifPresent(name -> System.out.println("Другий найдорожчий товар: " + name));

    List<Product> poorStore = List.of(new Product("Флешка", 300));
    Optional<String> emptyResult = ProductAnalyzer.getSecondMostExpensive(poorStore);

    if (emptyResult.isEmpty()) {
        System.out.println("Другого товару немає, список замалий.");
    }
}