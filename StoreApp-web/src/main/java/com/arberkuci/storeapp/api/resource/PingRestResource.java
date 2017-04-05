package com.arberkuci.storeapp.api.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by a.kuci on 4/5/2017.
 */
@Path("ping")
public class PingRestResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response ping() {
        return Response.ok().build();
    }

}
