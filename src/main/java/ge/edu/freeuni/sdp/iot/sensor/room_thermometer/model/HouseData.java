package ge.edu.freeuni.sdp.iot.sensor.room_thermometer.model;


import org.json.JSONObject;

public class HouseData {
    private String houseId;
    private String switchIpAddress;

    public HouseData(String houseId) {
        this.houseId = houseId;
    }

    public String getHouseId() {
        return houseId;
    }

    public void setHouseId(String houseId) {
        this.houseId = houseId;
    }

    public String getSwitchIpAddress() {
        return switchIpAddress;
    }

    public void setSwitchIpAddress(String switchIpAddress) {
        this.switchIpAddress = switchIpAddress;
    }

    public static HouseData fromJson(JSONObject jsonObject) {
        String houseId = jsonObject.getJSONObject("RowKey").getString("_");
        return new HouseData(houseId);
    }
}