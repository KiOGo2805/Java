void main() {
    int sides = 4;

    String shape = switch (sides) {
        case 3 -> "Трикутник";
        case 4 -> "Чотирикутник";
        case 5 -> "П'ятикутник";
        case 6 -> "Шестикутник";
        default -> "Інша фігура";
    };

    System.out.println("Кількість сторін: " + sides + ". Це: " + shape);
}