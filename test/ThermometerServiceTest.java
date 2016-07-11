import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;

import javax.ws.rs.core.Application;

public class ThermometerServiceTest  extends JerseyTest{

    @Override
    protected Application configure() {
        return new ResourceConfig(FakeThermometerService.class);
    }

}
