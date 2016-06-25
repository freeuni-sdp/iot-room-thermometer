package ge.edu.freeuni.sdp.iot.sensor.room_thermometer.core;


import ge.edu.freeuni.sdp.iot.sensor.room_thermometer.model.HouseData;

import java.util.List;


interface HouseRegistry {
    List<HouseData> getAllHouses();
    HouseData getHouseData(String houseId);
}