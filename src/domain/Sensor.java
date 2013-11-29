package domain;

public class Sensor {
    private String id;
    private String name;
    private Unit unit;
    
    public Sensor(String sensorId, String sensorName, Unit sensorUnit) {
        this.id = sensorId;
        this.name = sensorName;
        this.unit = sensorUnit;
    }
    
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Unit getUnit() {
        return unit;
    }

}
