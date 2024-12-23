package knorex.Company.assignment;

import java.time.LocalDateTime;
import java.util.*;

public class ParkingLot {
    private final Floor[] floors;
    private final CostStrategy costStrategy;
    private final Map<String, Vehicle> vehiclesByToken;
    private final Map<String, Vehicle> vehiclesByRegistration;
    private final Map<Vehicle, List<VehicleSpace>> occupiedSpaces;

    public ParkingLot(int numberOfFloors, int spacesPerFloor, CostStrategy costStrategy) {
        this.floors = new Floor[numberOfFloors];
        for (int i = 0; i < numberOfFloors; i++) {
            floors[i] = new Floor(i + 1, spacesPerFloor);
        }
        this.costStrategy = costStrategy;
        this.vehiclesByToken = new HashMap<>();
        this.vehiclesByRegistration = new HashMap<>();
        this.occupiedSpaces = new HashMap<>();
    }

    public String parkVehicle(Vehicle vehicle) throws ParkingException {
        if (vehiclesByRegistration.containsKey(vehicle.getRegistrationNumber())) {
            throw new ParkingException("Vehicle already parked");
        }

        for (Floor floor : floors) {
            if (floor.hasSpace(vehicle.getType())) {
                VehicleSpace[] spaces = floor.findAvailableSpaces(vehicle.getType());
                if (spaces != null) {
                    for (VehicleSpace space : spaces) {
                        space.occupy(vehicle);
                    }
                    vehiclesByToken.put(vehicle.getToken(), vehicle);
                    vehiclesByRegistration.put(vehicle.getRegistrationNumber(), vehicle);
                    occupiedSpaces.put(vehicle, Arrays.asList(spaces));
                    return vehicle.getToken();
                }
            }
        }
        throw new ParkingException("No available space for vehicle type: " + vehicle.getType());
    }

    public double removeVehicle(String token, LocalDateTime exitTime) throws ParkingException {
        Vehicle vehicle = vehiclesByToken.get(token);
        if (vehicle == null) {
            throw new ParkingException("Invalid token");
        }

        List<VehicleSpace> spaces = occupiedSpaces.get(vehicle);
        for (VehicleSpace space : spaces) {
            space.vacate();
        }

        vehiclesByToken.remove(token);
        vehiclesByRegistration.remove(vehicle.getRegistrationNumber());
        occupiedSpaces.remove(vehicle);

        return costStrategy.calculateCost(vehicle, exitTime);
    }

    public void displayStatus() {
        System.out.println("\nParking Lot Status:");
        for (Floor floor : floors) {
            System.out.println("\nFloor " + floor.getFloorNumber() + ":");
            VehicleSpace[] spaces = floor.getSpaces();
            for (VehicleSpace space : spaces) {
                System.out.printf("Space %d: %s\n",
                        space.getSpaceNumber(),
                        space.isAvailable() ? "Available" : "Occupied by " + space.getVehicle().getRegistrationNumber()
                );
            }
        }
    }

    public boolean checkAvailability(int floorNumber, VehicleType type) {
        if (floorNumber < 1 || floorNumber > floors.length) {
            return false;
        }
        return floors[floorNumber - 1].hasSpace(type);
    }
}
