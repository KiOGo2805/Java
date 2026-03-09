import java.util.HashMap;
import java.util.Map;

static class Student {
    int id;
    String name;
    int year;

    public Student(int id, String name, int year) {
        this.id = id;
        this.name = name;
        this.year = year;
    }

    @Override
    public String toString() {
        return "[ID: " + id + "] " + name + " (Курс: " + year + ")";
    }
}

static class StudentRegistry {
    Map<Integer, Student> registry = new HashMap<>();

    void addStudent(Student student) {
        registry.put(student.id, student);
        System.out.println("Додано: " + student.name);
    }

    void removeStudent(int id) {
        Student removed = registry.remove(id);
        if (removed != null) {
            System.out.println("Видалено: " + removed.name);
        } else {
            System.out.println("Студента з ID " + id + " не знайдено.");
        }
    }

    void findStudent(int id) {
        Student student = registry.get(id);
        if (student != null) {
            System.out.println("Знайдено: " + student);
        } else {
            System.out.println("Студента з ID " + id + " не знайдено.");
        }
    }

    void displayAll() {
        System.out.println("\n--- Список усіх студентів ---");
        if (registry.isEmpty()) {
            System.out.println("Реєстр порожній.");
            return;
        }
        for (Student student : registry.values()) {
            System.out.println(student);
        }
        System.out.println("-----------------------------\n");
    }
}

void main() {
    StudentRegistry myRegistry = new StudentRegistry();

    Student s1 = new Student(101, "Степан", 2);
    Student s2 = new Student(102, "Марія", 3);
    Student s3 = new Student(103, "Олег", 1);

    myRegistry.addStudent(s1);
    myRegistry.addStudent(s2);
    myRegistry.addStudent(s3);

    myRegistry.displayAll();

    myRegistry.findStudent(101);
    myRegistry.findStudent(999);

    myRegistry.removeStudent(102);

    myRegistry.displayAll();
}