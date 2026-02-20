// Файл task1.java
void main() {
    byte maxByte = 127; // Хардкодимо максимальне значення

    System.out.println("Було: " + maxByte);

    maxByte = (byte) (maxByte + 1); // Переповнення

    System.out.println("Стало після +1: " + maxByte);
}