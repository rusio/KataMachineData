package parser;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import domain.Sensor;
import domain.Unit;

public class HeaderParserTest {
    
    private HeaderParser parser;

    @Before
    public void setUp() {
        this.parser = new HeaderParser();
    }

    @Test(expected = IllegalArgumentException.class)
    public void emptyHeaderIsRejected() {
        parser.parse(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void emptyStringIsRejected() {
        parser.parse(new String[] {});
    }

    @Test(expected = IllegalArgumentException.class)
    public void headerLessThanThreeLinesIsRejected() {
        parser.parse(new String[] { "", "" });
    }

    @Test(expected = IllegalArgumentException.class)
    public void illegalUnitIsRejected() throws Exception {
        parser.parse(new String[] { "Datum;Uhrzeit;Sensor-0",
                "Datum;Uhrzeit;Sensor 0", "Datum;Uhrzeit;UnsupportedUnit" });
    }

    @Test
    public void headerWithoutSensorDefinitionsIsParsedToEmptyList()
            throws Exception {
        List<Sensor> sensors = parser.parse(new String[] { "Datum;Uhrzeit",
                "Datum;Uhrzeit", "Datum;Uhrzeit" });
        assertTrue(sensors.isEmpty());
    }

    @Test
    public void headerWithOneSensorDefinitionIsParsedCorrectly()
            throws Exception {
        List<Sensor> sensors = parser.parse(new String[] {
                "Datum;Uhrzeit;Sensor-0", "Datum;Uhrzeit;Sensor 0",
                "Datum;Uhrzeit;kJ" });
        assertEquals(1, sensors.size());
        assertEquals("Sensor-0", sensors.get(0).getId());
        assertEquals("Sensor 0", sensors.get(0).getName());
        assertEquals(Unit.KILO_JOULE, sensors.get(0).getUnit());
    }

    @Test
    public void headerWithTwoSensorDefinitionsIsParsedCorrectly()
            throws Exception {
        List<Sensor> sensors = parser.parse(new String[] {
                "Datum;Uhrzeit;Sensor-0;Sensor-1",
                "Datum;Uhrzeit;Sensor 0;Sensor 1", "Datum;Uhrzeit;kJ;mm" });
        assertEquals(2, sensors.size());
        assertEquals("Sensor-1", sensors.get(1).getId());
        assertEquals("Sensor 1", sensors.get(1).getName());
        assertEquals(Unit.MILLIMETER, sensors.get(1).getUnit());
    }

}
