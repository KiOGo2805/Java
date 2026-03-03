import java.util.Arrays;

void main() {
    int[] array1 = {1, 3, 5, 7};
    int[] array2 = {2, 4, 6, 8, 10};

    int[] result = new int[array1.length + array2.length];

    int i = 0, j = 0, k = 0;

    while (i < array1.length && j < array2.length) {
        if (array1[i] < array2[j]) {
            result[k++] = array1[i++];
        } else {
            result[k++] = array2[j++];
        }
    }

    while (i < array1.length) {
        result[k++] = array1[i++];
    }
    while (j < array2.length) {
        result[k++] = array2[j++];
    }

    System.out.println("Масив 1: " + Arrays.toString(array1));
    System.out.println("Масив 2: " + Arrays.toString(array2));
    System.out.println("Результат: " + Arrays.toString(result));
}