import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;

static class TemperatureAnalyzer {

    private static double calculateAverage(List<Integer> temperatures) {
        return temperatures.stream()
                .mapToInt(Integer::intValue)
                .average()
                .orElse(0.0);
    }

    static Optional<String> findHottestCity(Map<String, List<Integer>> cityTemperatures) {
        return cityTemperatures.entrySet().stream()
                .max(Comparator.comparingDouble(entry -> calculateAverage(entry.getValue())))
                .map(Map.Entry::getKey);
    }
}

void main() {
    Map<String, List<Integer>> weatherData = Map.of(
            "Київ", List.of(20, 22, 19, 23),
            "Одеса", List.of(25, 27, 26, 28, 29),
            "Львів", List.of(18, 19, 17, 20),
            "Чернівці", List.of(21, 23, 20, 24)
    );

    Optional<String> hottestCity = TemperatureAnalyzer.findHottestCity(weatherData);

    hottestCity.ifPresent(city ->
            System.out.println("Місто з найвищою середньою температурою: " + city)
    );
}