// Файл task2.java
void main() {
    int number = 54; // Хардкодимо число (наприклад, 55 ділиться на 5 і 11)

    if (number % 5 == 0 && number % 11 == 0) {
        System.out.println("Число " + number + " підходить.");
    } else {
        System.out.println("Число " + number + " не підходить.");
    }
}