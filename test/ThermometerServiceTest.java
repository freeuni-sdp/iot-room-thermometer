import ge.edu.freeuni.sdp.iot.sensor.room_thermometer.model.Temperature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Test;

import javax.ws.rs.core.Application;

import java.util.List;

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

    @Test
    public void get_house_temperature_empty_list_expect_null() {
        FakeRepository.instance().clear();
        List result= target("houses/1/floors")
                .request()
                .get(List.class);
        assertEquals(null, result);
    }

    @Test
    public void get_house_temperature_bad_id_expect_null() {
        FakeRepository repository = FakeRepository.instance();
        repository.clear();
        Temperature temperature = new Temperature(13, "1", "1");
        repository.addData("1", "1", temperature);

        List result= target("houses/2/floors")
                .request()
                .get(List.class);
        assertEquals(null, result);
    }

    @Test
    public void get_house_temperature_one_entry() {
        FakeRepository repository = FakeRepository.instance();
        repository.clear();
        Temperature temperature = new Temperature(13, "1", "1");
        repository.addData("1", "1", temperature);

        Temperature[] result = target("houses/1/floors")
                .request()
                .get(Temperature[].class);
        assertTrue(temperature.equals(result[0]));
        assertEquals(1, result.length);
    }

    @Test
    public void get_house_temperature_multiple_entry() {
        FakeRepository repository = FakeRepository.instance();
        repository.clear();
        for (int i = 0; i < 5; i++) {
            Temperature temperature = new Temperature(13, "1", Integer.toString(i));
            repository.addData("1", Integer.toString(i), temperature);
        }

        Temperature[] result = target("houses/1/floors")
                .request()
                .get(Temperature[].class);
        assertEquals(5, result.length);
    }

}