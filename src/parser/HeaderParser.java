package parser;

import java.util.ArrayList;
import java.util.List;

import domain.Sensor;
import domain.Unit;

/**
 * A parser for the header part of the CSV-like data format.
 *
 * The header format looks like this:
 * <pre>
 * Datum;Uhrzeit;Sensor0-ID;Sensor1-ID;...
 * Datum;Uhrzeit;Sensor0-Name;Sensor1-Name;...
 * Datum;Uhrzeit;Sensor0-Unit;Sensor1-Unit;...
 * </pre>
 *
 * The cells in the first two columns in the header always have
 * fixed values 'Datum' and 'Uhrzeit' and denote that the first two
 * columns in the data records contain timestamp information about
 * the measurement.
 *
 * Each subsequent column contains three values, each of which
 * holds metadata about the corresponding sensor - its ID, its
 * name and its unit of measurement.
 */
public class HeaderParser {
    private static final int FIRST_SENSOR_COLUMN_INDEX = 2;

    /**
     * TODO: comment
     */
    public List<Sensor> parse(String[] csvLines) {
        // TODO: how can we further simplify this method?
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
        if (unit.equals("kJ")){
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
