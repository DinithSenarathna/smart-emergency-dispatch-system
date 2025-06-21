package model;

public class Station {
    private EmergencyType type;
    private int location;
    private int availableUnits;

    public Station(EmergencyType type, int location, int availableUnits) {
        this.type = type;
        this.location = location;
        this.availableUnits = availableUnits;
    }

    public EmergencyType getType() { return type; }
    public int getLocation() { return location; }
    public int getAvailableUnits() { return availableUnits; }
    public void assignUnit() {
        if (availableUnits > 0) availableUnits--;
    }
}
