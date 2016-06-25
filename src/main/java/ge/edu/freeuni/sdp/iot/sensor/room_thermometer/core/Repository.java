package ge.edu.freeuni.sdp.iot.sensor.room_thermometer.core;


import ge.edu.freeuni.sdp.iot.sensor.room_thermometer.model.Temperature;

import java.util.List;


public interface Repository {

    void addData(String houseId, String floorId, Temperature temperature);
    Temperature getFloorTemperature(String houseId, String floorId);
    List<Temperature> getHouseTemperature(String houseId);

}
