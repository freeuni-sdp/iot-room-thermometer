import ge.edu.freeuni.sdp.iot.sensor.room_thermometer.core.Repository;
import ge.edu.freeuni.sdp.iot.sensor.room_thermometer.model.Temperature;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FakeRepository implements Repository {

    private static FakeRepository instance;
    private HashMap<String, HashMap<String, Temperature>> map;

    private FakeRepository() {
        map = new HashMap<>();
    }

    public static FakeRepository instance() {
        if (instance==null) {
            instance = new FakeRepository();
        }
        return instance;
    }

    @Override
    public void addData(String houseId, String floorId, Temperature temperature) {
        HashMap<String, Temperature> temp;
        if (map.containsKey(houseId))
            temp =  map.get(houseId);
        else
            temp = new HashMap<>();
        temp.put(floorId, temperature);

        map.put(houseId, temp);
    }

    @Override
    public Temperature getFloorTemperature(String houseId, String floorId) {
        if (!map.containsKey(houseId))
            return null;

        Temperature temp = map.get(houseId).get(floorId);
        if (temp == null)
            return null;

        return temp;
    }

    @Override
    public List<Temperature> getHouseTemperature(String houseId) {
        if (!map.containsKey(houseId))
            return null;

        List<Temperature> tempList = new ArrayList<>(map.get(houseId).values());
        if (tempList.isEmpty())
            return null;

        return tempList;
    }

    public void clear() {
        map.clear();
    }
}
