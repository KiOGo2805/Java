static class GenericAlgorithms {

    static <T extends Comparable<T>> T findMax(T[] array) {
        if (array == null || array.length == 0) {
            return null;
        }

        T max = array[0];

        for (int i = 1; i < array.length; i++) {
            if (array[i].compareTo(max) > 0) {
                max = array[i];
            }
        }

        return max;
    }
}

void main() {
    Integer[] intArray = {15, 42, 8, 99, 23};
    System.out.println("Найбільший Integer: " + GenericAlgorithms.findMax(intArray));

    Double[] doubleArray = {3.14, 9.81, 2.71, 1.61};
    System.out.println("Найбільший Double: " + GenericAlgorithms.findMax(doubleArray));

    Character[] charArray = {'A', 'Z', 'K', 'B'};
    System.out.println("Найбільший Character: " + GenericAlgorithms.findMax(charArray));

    String[] stringArray = {"Яблуко", "Зебра", "Банан", "Апельсин"};
    System.out.println("Найбільший String: " + GenericAlgorithms.findMax(stringArray));
}