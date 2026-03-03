import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

void main() {
    List<String> cities = new ArrayList<>(List.of(
            "Київ", "Чернівці", "Львів", "Одеса", "Харків", "Дніпро"
    ));

    System.out.println("Початковий список: " + cities);

    shuffleCities(cities);
    System.out.println("Перемішаний список: " + cities);

    sortCities(cities);
    System.out.println("Відсортований за алфавітом: " + cities);
}

void shuffleCities(List<String> list) {
    Collections.shuffle(list);
}

void sortCities(List<String> list) {
    Collections.sort(list);
}