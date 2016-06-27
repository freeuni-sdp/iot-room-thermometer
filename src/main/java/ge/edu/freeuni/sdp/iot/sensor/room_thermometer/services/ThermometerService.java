package ge.edu.freeuni.sdp.iot.sensor.room_thermometer.services;

import ge.edu.freeuni.sdp.iot.sensor.room_thermometer.core.Repository;
import ge.edu.freeuni.sdp.iot.sensor.room_thermometer.core.RepositoryFactory;
import ge.edu.freeuni.sdp.iot.sensor.room_thermometer.model.Temperature;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/houses")
@Consumes({MediaType.APPLICATION_JSON})
@Produces({MediaType.APPLICATION_JSON})
public class ThermometerService {

    @GET
    @Path("{house_id}/floors/{floor_id}")
    public Temperature getFloorTemperature(@PathParam("house_id") String houseId,
                                        @PathParam("floor_id") String floorId){

        return getRepository().getFloorTemperature(houseId, floorId);
    }

    @GET
    @Path("{house_id}/floors/")
    public List<Temperature> getHouseTemperature(@PathParam("house_id") String houseId){
        return getRepository().getHouseTemperature(houseId);
    }

    @POST
    @Path("{house_id}/floors/{floor_id}")
    public Response receiveHouseTemperature(@PathParam("house_id") String houseId,
                                        @PathParam("floor_id") String floorId,
                                        Temperature temperature){
        temperature.setHouseId(houseId);
        temperature.setFloorId(floorId);
        getRepository().addData(houseId, floorId, temperature);
        return Response.ok().build();
    }

    public Repository getRepository() {
        return RepositoryFactory.instance();
    }
}
