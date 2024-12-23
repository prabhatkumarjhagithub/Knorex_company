package knorex.Company.assignment;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;

public class HourlyCostStrategy implements CostStrategy {
    private final Map<VehicleType, Double> hourlyRates;
    private final Currency currency;

    public HourlyCostStrategy(Currency currency) {
        this.currency = currency;
        this.hourlyRates = new HashMap<>();

        // Default rates in the specified currency
        setRate(VehicleType.BIKE, 10.0);
        setRate(VehicleType.CAR, 20.0);
        setRate(VehicleType.SPORTS_CAR, 25.0);
        setRate(VehicleType.TRUCK, 30.0);
        setRate(VehicleType.BUS, 30.0);
    }

    @Override
    public double calculateCost(Vehicle vehicle, LocalDateTime exitTime) {
        long hours = ChronoUnit.HOURS.between(vehicle.getEntryTime(), exitTime);
        if (hours == 0) hours = 1; // Minimum 1 hour charge
        return hours * hourlyRates.get(vehicle.getType());
    }

    @Override
    public void setRate(VehicleType type, double hourlyRate) {
        hourlyRates.put(type, hourlyRate);
    }

    @Override
    public String formatCost(double cost) {
        return String.format("%s%.2f", currency.getSymbol(), cost);
    }
}