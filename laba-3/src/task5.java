import java.util.List;
import java.util.Objects;

static class Pair<T, U> {
    private final T first;
    private final U second;

    public Pair(T first, U second) {
        this.first = first;
        this.second = second;
    }


    public boolean isEqual(Pair<T, U> other) {
        if (other == null) return false;
        return Objects.equals(this.first, other.first) &&
                Objects.equals(this.second, other.second);
    }

    public String toString() {
        return "Pair[" + first + ", " + second + "]";
    }
}

void main() {
    System.out.println("--- Тест 1: Pair<Integer, String> ---");
    Pair<Integer, String> p1 = new Pair<>(1, "Степан");
    Pair<Integer, String> p2 = new Pair<>(1, "Степан");
    Pair<Integer, String> p3 = new Pair<>(2, "Олег");

    System.out.println("Пара 1: " + p1);
    System.out.println("Пара 2: " + p2);
    System.out.println("Пара 3: " + p3);

    System.out.println("Пара 1 == Пара 2? -> " + p1.isEqual(p2));
    System.out.println("Пара 1 == Пара 3? -> " + p1.isEqual(p3));

    System.out.println("\n--- Тест 2: Pair<String, List<Integer>> ---");
    Pair<String, List<Integer>> grades1 = new Pair<>("Оцінки за семестр", List.of(90, 85, 100));
    Pair<String, List<Integer>> grades2 = new Pair<>("Оцінки за семестр", List.of(90, 85, 100));
    Pair<String, List<Integer>> grades3 = new Pair<>("Оцінки за семестр", List.of(70, 75, 80));

    System.out.println("Список 1: " + grades1);
    System.out.println("Список 2: " + grades2);
    System.out.println("Список 3: " + grades3);

    System.out.println("Список 1 == Список 2? -> " + grades1.isEqual(grades2));
    System.out.println("Список 1 == Список 3? -> " + grades1.isEqual(grades3));
}