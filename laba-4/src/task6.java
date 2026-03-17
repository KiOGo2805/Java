import java.util.List;

record Person(String name, List<Person> friends) {}

static class FriendAnalyzer {

    static List<String> getUFNU(List<Person> people) {
        return people.stream()
                .flatMap(person -> person.friends().stream())
                .distinct()
                .map(friend -> friend.name().toUpperCase())
                .toList();
    }
}

void main() {
    Person anna = new Person("Анна", List.of());
    Person oleg = new Person("Олег", List.of());
    Person maria = new Person("Марія", List.of());

    Person stepan = new Person("Степан", List.of(anna, oleg));
    Person yana = new Person("Яна", List.of(anna, maria));

    List<Person> peopleList = List.of(stepan, yana);

    List<String> uniqueUppercaseFriends = FriendAnalyzer.getUFNU(peopleList);

    System.out.println("Унікальні імена друзів (ВЕЛИКИМИ ЛІТЕРАМИ):");
    System.out.println(uniqueUppercaseFriends);
}