package ge.edu.freeuni.sdp.iot.sensor.room_thermometer.model;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Temperature {

    @JsonProperty("temperature")
    private double temperature;

    @JsonProperty("house_id")
    private String houseId;

    @JsonProperty("floor_id")
    private String floorId;

    public Temperature(double temperature, String houseId, String floorId) {
        this.temperature = temperature;
        this.houseId = houseId;
        this.floorId = floorId;
    }

    public Temperature() {
    }

    public String getHouseId() {
        return houseId;
    }

    public void setHouseId(String houseId) {
        this.houseId = houseId;
    }

    public String getFloorId() {
        return floorId;
    }

    public void setFloorId(String floorId) {
        this.floorId = floorId;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;

        Temperature other = (Temperature) obj;
        if (houseId == null) {
            if (other.houseId != null)
                return false;
        } else if (!houseId.equals(other.houseId))
            return false;

        if (floorId == null) {
            if (other.floorId != null)
                return false;
        } else if (!floorId.equals(other.floorId))
            return false;

        return temperature == other.temperature;
    }
}
