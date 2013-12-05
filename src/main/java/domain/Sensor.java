package domain;

/**
 * This is a Value Object:
 * - final class
 * - final fields
 * - no mutable fields
 * - no state-changing methods (no setters)
 * - equals() and hashCode()
 */
public final class Sensor {
    private final String id;
    private final String name;
    private final Unit unit;
    
    public Sensor(String sensorId, String sensorName, Unit sensorUnit) {
        // TODO: check parameters and enforce valid values
        // (and also re-generate equals() and hashCode() without null-checks)
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Sensor sensor = (Sensor) o;

        if (id != null ? !id.equals(sensor.id) : sensor.id != null) return false;
        if (name != null ? !name.equals(sensor.name) : sensor.name != null) return false;
        if (unit != sensor.unit) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (unit != null ? unit.hashCode() : 0);
        return result;
    }
}
