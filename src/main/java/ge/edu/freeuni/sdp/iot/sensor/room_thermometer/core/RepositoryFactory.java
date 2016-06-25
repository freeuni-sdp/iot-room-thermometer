package ge.edu.freeuni.sdp.iot.sensor.room_thermometer.core;


public class RepositoryFactory {
    private static Repository instance;

    public static Repository create() {
        if (instance == null){
            instance = new InMemoryRepository(new HouseRegistryProxy());
        }

        return instance;
    }
}
