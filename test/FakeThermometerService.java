import ge.edu.freeuni.sdp.iot.sensor.room_thermometer.core.Repository;
import ge.edu.freeuni.sdp.iot.sensor.room_thermometer.services.ThermometerService;

public class FakeThermometerService extends ThermometerService {

    @Override
    public Repository getRepository() {
        return FakeRepository.instance();
    }
}
