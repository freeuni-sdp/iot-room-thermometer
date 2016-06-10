package ge.edu.freeuni.sdp.iot.sensor.room_thermometer.core;

import javax.ws.rs.*;
import javax.ws.rs.core.*;

@Path("ping")
public class PingService {

  @GET
  public Response get() {
    return Response.ok().build();
  }
}
