import ge.edu.freeuni.sdp.iot.sensor.room_thermometer.model.Temperature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Test;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Application;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ThermometerServiceTest  extends JerseyTest{

    @Override
    protected Application configure() {
        return new ResourceConfig(FakeThermometerService.class);
    }

    @Test
    public void get_temperature_bad_ids_expect_null() {

        FakeRepository.instance().clear();

        Temperature result = target("houses/123/floors/321")
                .request()
                .get(Temperature.class);
        assertEquals(null, result);
    }

    @Test
    public void get_temperature_wrong_floor_id_expect_null() {
        FakeRepository repository = FakeRepository.instance();
        repository.clear();
        Temperature temperature = new Temperature(13, "1", "1");
        repository.addData("1", "1", temperature);

        Temperature result = target("houses/1/floors/3")
                .request()
                .get(Temperature.class);
        assertEquals(null, result);
    }

    @Test
    public void get_temperature_wrong_house_id_expect_null() {
        FakeRepository repository = FakeRepository.instance();
        repository.clear();
        Temperature temperature = new Temperature(13, "1", "1");
        repository.addData("1", "1", temperature);

        Temperature result = target("houses/2/floors/1")
                .request()
                .get(Temperature.class);
        assertEquals(null, result);
    }

    @Test
    public void get_temperature_correct_ids() {
        FakeRepository repository = FakeRepository.instance();
        repository.clear();
        Temperature temperature = new Temperature(13, "1", "1");
        repository.addData("1", "1", temperature);

        Temperature result = target("houses/1/floors/1")
                .request()
                .get(Temperature.class);
        assertTrue(temperature.equals(result));
    }

}
