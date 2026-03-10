static class Box<T> {
    private T item;

    public void put(T item) {
        if (this.item != null) {
            System.out.println("Коробка вже зайнята.");
        } else {
            this.item = item;
            System.out.println("У коробку поклали: " + item);
        }
    }

    public void take() {
        if (this.item == null) {
            System.out.println("Коробка порожня.");
        } else {
            T retrievedItem = this.item;
            this.item = null;
            System.out.println("З коробки дістали: " + retrievedItem);
        }
    }
}

void main() {
    System.out.println("--- Тест зі String ---");
    Box<String> stringBox = new Box<>();
    stringBox.put("Ноутбук");
    stringBox.take();
    stringBox.take();

    System.out.println("\n--- Тест з Integer ---");
    Box<Integer> intBox = new Box<>();
    intBox.put(4070);
    intBox.put(3050);
    intBox.take();

    System.out.println("\n--- Тест з Double ---");
    Box<Double> doubleBox = new Box<>();
    doubleBox.put(3.1415);
    doubleBox.take();
}