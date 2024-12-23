package knorex.Company.assignment;


public enum VehicleType {
    CAR(1),
    SPORTS_CAR(1),
    TRUCK(2),
    BUS(2),
    BIKE(1);

    private final int spacesRequired;

    VehicleType(int spacesRequired) {
        this.spacesRequired = spacesRequired;
    }

    public int getSpacesRequired() {
        return spacesRequired;
    }
}
