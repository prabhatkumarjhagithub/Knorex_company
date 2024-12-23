package knorex.Company.assignment;

import java.time.LocalDateTime;

public interface CostStrategy {
    double calculateCost(Vehicle vehicle, LocalDateTime exitTime);
    void setRate(VehicleType type, double hourlyRate);
    String formatCost(double cost);
}