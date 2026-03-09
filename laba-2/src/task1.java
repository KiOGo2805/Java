static class OutOfRangeException extends Exception {
    public OutOfRangeException(String message) {
        super(message);
    }
}

void main() {
    double[] testTemperatures = { 68.0, -150.0, 100.0, 250.0 };

    for (double tempF : testTemperatures) {
        try {
            double tempC = convertToCelsius(tempF);
            System.out.println(tempF + "°F дорівнює " + tempC + "°C");
        } catch (OutOfRangeException e) {
            System.out.println("ПОМИЛКА для " + tempF + "°F: " + e.getMessage());
        }
    }
}

double convertToCelsius(double fahrenheit) throws OutOfRangeException {
    if (fahrenheit < -100.0 || fahrenheit > 200.0) {
        throw new OutOfRangeException("Температура виходить за межі діапазону людської діяльності!");
    }
    double exactCelsius = (fahrenheit - 32) * 5 / 9;

    return Math.round(exactCelsius * 10.0) / 10.0;
}
