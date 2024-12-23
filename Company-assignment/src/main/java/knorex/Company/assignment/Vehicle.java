package knorex.Company.assignment;

import java.time.LocalDateTime;
import java.util.UUID;

public class Vehicle {
    private String registrationNumber;
    private VehicleType type;
    private String color;
    private LocalDateTime entryTime;
    private String token;

    public Vehicle(String registrationNumber, VehicleType type, String color) {
        this.registrationNumber = registrationNumber;
        this.type = type;
        this.color = color;
        this.entryTime = LocalDateTime.now();
        this.token = UUID.randomUUID().toString();
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public VehicleType getType() {
        return type;
    }

    public String getColor() {
        return color;
    }

    public LocalDateTime getEntryTime() {
        return entryTime;
    }

    public String getToken() {
        return token;
    }
}