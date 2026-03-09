import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

static class CollectionUtils {

    static <T> Set<T> getUniqueElements(List<T> list) {
        return new HashSet<>(list);
    }

    static <T> Map<T, Integer> countOccurrences(List<T> list) {
        Map<T, Integer> counts = new HashMap<>();

        for (T item : list) {
            counts.put(item, counts.getOrDefault(item, 0) + 1);
        }

        return counts;
    }
}

void main() {
    List<String> words = new ArrayList<>(List.of(
            "яблуко", "банан", "яблуко", "апельсин", "банан", "яблуко", "ківи"
    ));

    System.out.println("Початковий список: " + words);

    Set<String> uniqueWords = CollectionUtils.getUniqueElements(words);
    System.out.println("\nУнікальні елементи (Set): " + uniqueWords);

    Map<String, Integer> wordCounts = CollectionUtils.countOccurrences(words);
    System.out.println("\nКількість входжень (Map):");

    for (Map.Entry<String, Integer> entry : wordCounts.entrySet()) {
        System.out.println(entry.getKey() + " -> " + entry.getValue() + " шт.");
    }
}