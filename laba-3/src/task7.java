import java.util.ArrayList;
import java.util.List;

static class LowerBoundDemo {

    static void addToList(List<? super Integer> list) {
        for (int i = 1; i <= 10; i++) {
            list.add(i);
        }
        System.out.println("Результат після додавання: " + list);
    }
}

void main() {
    System.out.println("--- Тест 1: List<Integer> ---");
    List<Integer> intList = new ArrayList<>();
    LowerBoundDemo.addToList(intList);

    System.out.println("\n--- Тест 2: List<Number> ---");
    List<Number> numList = new ArrayList<>();
    numList.add(3.14);
    numList.add(9.81);
    System.out.println("Було до виклику методу: " + numList);
    LowerBoundDemo.addToList(numList);

    System.out.println("\n--- Тест 3: List<Object> ---");
    List<Object> objList = new ArrayList<>();
    objList.add("Якийсь текст");
    System.out.println("Було до виклику методу: " + objList);
    LowerBoundDemo.addToList(objList);
}