/**
 * Created by Nika Doghonadze
 */

import ge.edu.freeuni.sdp.iot.sensor.room_thermometer.services.PingService;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Test;

import javax.ws.rs.core.Application;
import javax.ws.rs.core.Response;

import static org.junit.Assert.assertEquals;

/**
 * Created by Nika Doghonadze.
 */
public class PingServiceTest extends JerseyTest {

    @Override
    protected Application configure() {
        return new ResourceConfig(PingService.class);
    }

    @Test
    public void testGetPing() {
        Response basilResponse = target("ping").request().get();
        assertEquals(basilResponse.getStatus(), Response.Status.OK.getStatusCode());
    }
}
