void main() {
    int number = 7;
    int i = 1;

    System.out.println("Таблиця множення для числа " + number + ":");

    while (i < 10) {
        System.out.println(number + " * " + i + " = " + (number * i));
        i++;
    }
}