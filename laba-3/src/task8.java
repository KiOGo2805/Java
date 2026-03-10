import java.util.ArrayList;
import java.util.List;

abstract static class Animal {
    abstract void makeSound();
}

static class Dog extends Animal {
    void makeSound() {
        System.out.println("Гав-гав!");
    }
}

static class Cat extends Animal {
    void makeSound() {
        System.out.println("Мяу-мяу!");
    }
}

static class Labrador extends Dog {
    void makeSound() {
        System.out.println("Гав-гав! (лабрадор)");
    }
}

static class AnimalShelter {
    List<Dog> dogsOnly = new ArrayList<>();
    List<Animal> allAnimals = new ArrayList<>();

    void addAnimals(List<? super Dog> list, Dog dog) {
        list.add(dog);
        System.out.println("Додано песика до списку.");
    }

    void printAnimalSounds(List<? extends Animal> animals) {
        for (Animal animal : animals) {
            animal.makeSound();
        }
    }
}

void main() {
    AnimalShelter shelter = new AnimalShelter();

    Dog regularDog = new Dog();
    Labrador myLabrador = new Labrador();
    Cat myCat = new Cat();

    System.out.println("--- 1. Додаємо собак ---");
    shelter.addAnimals(shelter.dogsOnly, regularDog);
    shelter.addAnimals(shelter.dogsOnly, myLabrador);

    System.out.println("\n--- 2. Додаємо тварин у загальний список ---");
    shelter.allAnimals.add(myCat);
    shelter.addAnimals(shelter.allAnimals, regularDog);

    System.out.println("\n--- 3. Слухаємо звуки у собачому вольєрі ---");
    shelter.printAnimalSounds(shelter.dogsOnly);

    System.out.println("\n--- 4. Слухаємо звуки у всьому притулку ---");
    shelter.printAnimalSounds(shelter.allAnimals);
}