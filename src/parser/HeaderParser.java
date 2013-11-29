package parser;

import java.util.ArrayList;
import java.util.List;

import domain.Sensor;
import domain.Unit;

public class HeaderParser {
    private static final int FIRST_SENSOR_COLUMN_INDEX = 2;

    public List<Sensor> parse(String[] csvLines) {
        checkCsvInput(csvLines);
        List<Sensor> sensors = new ArrayList<Sensor>();
        String[] columns0 = csvLines[0].split(";");
        String[] columns1 = csvLines[1].split(";");
        String[] columns2 = csvLines[2].split(";");

        for (int col = FIRST_SENSOR_COLUMN_INDEX; col < columns0.length; col++) {
            String sensorId = columns0[col];
            String sensorName = columns1[col];
            Unit sensorUnit = getUnit(columns2[col]);
            sensors.add(new Sensor(sensorId, sensorName, sensorUnit));
        }

        return sensors;
    }

    private Unit getUnit(String unit) {
        if(unit.equals("kJ")){
            return Unit.KILO_JOULE;
        }
        if (unit.equals("mm")) {
            return Unit.MILIMETER;
        }
        throw new IllegalArgumentException("Unknown type of Unit: " + unit);
    }

    private void checkCsvInput(String[] csvLines) {
        if (csvLines == null) {
            throw new IllegalArgumentException("CSV must not be null!");
        }
        if (csvLines.length < 3) {
            throw new IllegalArgumentException(
                    "CSV must have at least 3 lines!");
        }
    }
}
