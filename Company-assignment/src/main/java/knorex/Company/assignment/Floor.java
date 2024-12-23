package knorex.Company.assignment;

public class Floor {
    private int floorNumber;
    private VehicleSpace[] spaces;
    private int totalSpaces;
    private int availableSpaces;

    public Floor(int floorNumber, int totalSpaces) {
        this.floorNumber = floorNumber;
        this.totalSpaces = totalSpaces;
        this.availableSpaces = totalSpaces;
        this.spaces = new VehicleSpace[totalSpaces];

        for (int i = 0; i < totalSpaces; i++) {
            spaces[i] = new VehicleSpace(i + 1);
        }
    }

    public boolean hasSpace(VehicleType type) {
        int requiredSpaces = type.getSpacesRequired();
        int consecutiveSpaces = 0;

        for (VehicleSpace space : spaces) {
            if (space.isAvailable()) {
                consecutiveSpaces++;
                if (consecutiveSpaces >= requiredSpaces) {
                    return true;
                }
            } else {
                consecutiveSpaces = 0;
            }
        }
        return false;
    }

    public VehicleSpace[] findAvailableSpaces(VehicleType type) {
        int requiredSpaces = type.getSpacesRequired();
        int consecutiveSpaces = 0;
        int startIndex = -1;

        for (int i = 0; i < spaces.length; i++) {
            if (spaces[i].isAvailable()) {
                if (consecutiveSpaces == 0) {
                    startIndex = i;
                }
                consecutiveSpaces++;
                if (consecutiveSpaces >= requiredSpaces) {
                    VehicleSpace[] result = new VehicleSpace[requiredSpaces];
                    for (int j = 0; j < requiredSpaces; j++) {
                        result[j] = spaces[startIndex + j];
                    }
                    return result;
                }
            } else {
                consecutiveSpaces = 0;
                startIndex = -1;
            }
        }
        return null;
    }

    public int getFloorNumber() {
        return floorNumber;
    }

    public int getAvailableSpaces() {
        return availableSpaces;
    }

    public VehicleSpace[] getSpaces() {
        return spaces;
    }
}