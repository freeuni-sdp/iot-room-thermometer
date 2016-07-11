package ge.edu.freeuni.sdp.iot.sensor.room_thermometer.core;


import ge.edu.freeuni.sdp.iot.sensor.room_thermometer.model.Temperature;

import javax.ws.rs.NotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class InMemoryRepository implements  Repository{

    private final HouseRegistry houseRegistry;
    private HashMap<String, HashMap<String, Temperature>> houseTemperature;

    public InMemoryRepository(HouseRegistry houseRegistry) {
        this.houseTemperature = new HashMap<>();
        this.houseRegistry = houseRegistry;
    }

    @Override
    public void addData(String houseId, String floorId, Temperature temperature) {
        if (houseRegistry.getHouseData(houseId) == null)
            throw new NotFoundException();
        HashMap<String, Temperature> temp;
        if (houseTemperature.containsKey(houseId))
            temp =  houseTemperature.get(houseId);
        else
            temp = new HashMap<>();
        temp.put(floorId, temperature);

        houseTemperature.put(houseId, temp);
    }

    @Override
    public Temperature getFloorTemperature(String houseId, String floorId) {
        if (houseRegistry.getHouseData(houseId) == null)
            throw new NotFoundException();

        if (!houseTemperature.containsKey(houseId))
            return null;
        Temperature temp = houseTemperature.get(houseId).get(floorId);
        if (temp == null)
            return null;
        return temp;
    }

    @Override
    public List<Temperature> getHouseTemperature(String houseId) {
        if (houseRegistry.getHouseData(houseId) == null)
            throw new NotFoundException();

        if (!houseTemperature.containsKey(houseId))
            return null;

        List<Temperature> tempList = new ArrayList<>(houseTemperature.get(houseId).values());
        if (tempList.isEmpty())
            return null;
        return tempList;
    }

}
