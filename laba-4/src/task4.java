import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

record Employee(String name, double salary) {

    public String toString() {
        return name + " ($" + salary + ")";
    }
}

static class EmployeeAnalyzer {

    static String getSalaryRange(Employee emp) {
        double salary = emp.salary();
        if (salary < 3000) return "< 3000";
        if (salary <= 5000) return "3000-5000";
        return "> 5000";
    }

    static Map<String, Optional<Employee>> getTopEarnersByRange(List<Employee> employees) {
        return employees.stream()
                .collect(Collectors.groupingBy(
                        EmployeeAnalyzer::getSalaryRange,
                        Collectors.maxBy(Comparator.comparingDouble(Employee::salary))
                ));
    }
}

void main() {
    List<Employee> team = List.of(
            new Employee("Степан", 2500),
            new Employee("Марія", 2900),
            new Employee("Олег", 4000),
            new Employee("Анна", 4800),
            new Employee("Іван", 6000),
            new Employee("Яна", 8500)
    );

    Map<String, Optional<Employee>> topEarners = EmployeeAnalyzer.getTopEarnersByRange(team);

    System.out.println("--- Найбільші зарплати по групах ---");
    topEarners.forEach((range, employeeOpt) -> {
        employeeOpt.ifPresent(emp -> System.out.println("Діапазон " + range + ": " + emp.name() + " з ЗП " + emp.salary())
        );
    });
}