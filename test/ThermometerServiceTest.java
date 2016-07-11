import ge.edu.freeuni.sdp.iot.sensor.room_thermometer.model.Temperature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Test;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

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
    public void post_temperature_single_house_expect_ok() {
        FakeRepository repository = FakeRepository.instance();
        repository.clear();
        Temperature temperature = new Temperature(13.5, "1", "1");

        Response response = target("houses/1/floors/1")
                .request()
                .post(Entity.entity(temperature, MediaType.APPLICATION_JSON_TYPE));

        assertEquals(response.getStatus(), Response.Status.OK.getStatusCode());
    }


    @Test
    public void post_temperature_single_house_one_floor_expect_repository_changed() {
        FakeRepository repository = FakeRepository.instance();
        repository.clear();

        Temperature temperatureFloor1 = new Temperature(25.2, "1", "1");

        Response response = target("houses/1/floors/1")
                .request()
                .post(Entity.entity(temperatureFloor1, MediaType.APPLICATION_JSON_TYPE));

        assertEquals(response.getStatus(), Response.Status.OK.getStatusCode());
        assertTrue(temperatureFloor1.equals(repository.getFloorTemperature("1", "1")));
    }


    @Test
    public void post_temperature_single_house_two_floors_expect_repository_changed() {
        FakeRepository repository = FakeRepository.instance();
        repository.clear();

        Temperature temperatureFloor1 = new Temperature(25.2, "1", "1");
        Temperature temperatureFloor2 = new Temperature(22.2, "1", "2");

        Response response1 = target("houses/1/floors/1")
                .request()
                .post(Entity.entity(temperatureFloor1, MediaType.APPLICATION_JSON_TYPE));

        Response response2 = target("houses/1/floors/2")
                .request()
                .post(Entity.entity(temperatureFloor2, MediaType.APPLICATION_JSON_TYPE));


        assertEquals(response1.getStatus(), Response.Status.OK.getStatusCode());
        assertEquals(response2.getStatus(), Response.Status.OK.getStatusCode());

        assertTrue(temperatureFloor1.equals(repository.getFloorTemperature("1", "1")));
        assertTrue(temperatureFloor2.equals(repository.getFloorTemperature("1", "2")));
    }

    @Test
    public void post_temperature_single_house_two_floors_post_twice() {
        FakeRepository repository = FakeRepository.instance();
        repository.clear();

        Temperature temperatureFloor1 = new Temperature(25.2, "house1", "floor1");
        Temperature temperatureFloor2 = new Temperature(25.4, "house1", "floor2");
        Temperature temperatureFloor1Second = new Temperature(26.2, "house1", "floor1");

        Response response1 = target("houses/house1/floors/floor1")
                .request()
                .post(Entity.entity(temperatureFloor1, MediaType.APPLICATION_JSON_TYPE));

        Response response2 = target("houses/house1/floors/floor2")
                .request()
                .post(Entity.entity(temperatureFloor2, MediaType.APPLICATION_JSON_TYPE));

        Response response1Second = target("houses/house1/floors/floor1")
                .request()
                .post(Entity.entity(temperatureFloor1Second, MediaType.APPLICATION_JSON_TYPE));



        assertEquals(response1.getStatus(), Response.Status.OK.getStatusCode());
        assertEquals(response2.getStatus(), Response.Status.OK.getStatusCode());
        assertEquals(response1Second.getStatus(), Response.Status.OK.getStatusCode());

        assertTrue(temperatureFloor1Second.equals(repository.getFloorTemperature("house1", "floor1")));
        assertTrue(temperatureFloor2.equals(repository.getFloorTemperature("house1", "floor2")));
    }


    @Test
    public void post_temperature_two_houses_one_floor() {
        FakeRepository repository = FakeRepository.instance();
        repository.clear();

        Temperature temperatureHouse1Floor1 = new Temperature(25.2, "house1", "floor1");
        Temperature temperatureHouse2Floor1 = new Temperature(24.2, "house2", "floor1");

        Response response1 = target("houses/house1/floors/floor1")
                .request()
                .post(Entity.entity(temperatureHouse1Floor1, MediaType.APPLICATION_JSON_TYPE));

        Response response2 = target("houses/house2/floors/floor1")
                .request()
                .post(Entity.entity(temperatureHouse2Floor1, MediaType.APPLICATION_JSON_TYPE));


        assertEquals(response1.getStatus(), Response.Status.OK.getStatusCode());
        assertEquals(response2.getStatus(), Response.Status.OK.getStatusCode());

        assertTrue(temperatureHouse1Floor1.equals(repository.getFloorTemperature("house1", "floor1")));
        assertTrue(temperatureHouse2Floor1.equals(repository.getFloorTemperature("house2", "floor1")));
    }
}
