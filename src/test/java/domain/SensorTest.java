package domain;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SensorTest {

  @Test
  public void valueObjectSemantics() throws Exception {
    Sensor s1 = new Sensor("id", "name", Unit.KILO_JOULE);
    Sensor s2 = new Sensor("id", "name", Unit.KILO_JOULE);
    assertEquals(s1, s2);
  }

  @Test
  public void contractOfEqualsAndHashCode() throws Exception {
    EqualsVerifier.forClass(Sensor.class).verify();
  }

}
