package com.arberkuci.storeapp.web.api.user;

import com.arberkuci.storeapp.ejb.user.api.UserFacade;
import com.arberkuci.storeapp.ejb.user.dto.UserDto;
import com.arberkuci.storeapp.web.json.JsonBuilder;
import org.apache.log4j.Logger;

import javax.inject.Inject;

import javax.json.Json;
import javax.json.JsonArray;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path(value = "users")
public class UserRestResource {

    @Inject
    UserFacade userFacade;

    @Inject
    JsonBuilder jsonBuilder;

    private static final Logger logger = Logger.getLogger(UserRestResource.class);


    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response storeUser(UserDto userDto) {
        Response response;
        try {
            UserDto storedUser = userFacade.storeUser(userDto);
            //remove this part of code from rest endpoint!
            response = Response.status(Response.Status.CREATED).entity(Json.createArrayBuilder().
                    add(Json.createObjectBuilder().
                            add("id", storedUser.getId()).
                            add("name", storedUser.getName()).
                            add("surName", storedUser.getSurName())).build()).build();
        } catch (Exception e) {
            logger.error(e);
            response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.fillInStackTrace()).build();
        }
        return response;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public Response findUserById(@PathParam("id") Long id) {
        Response response;
        try {
            UserDto foundUser = userFacade.findByUserById(id);
            if (foundUser == null) {
                response = Response.status(Response.Status.NOT_FOUND).build();
            } else {
                //TODO: Remove this part of code from this rest endpoint!
                response = Response.ok().entity(Json.createArrayBuilder().
                        add(Json.createObjectBuilder().
                                add("id", foundUser.getId()).
                                add("name", foundUser.getName()).
                                add("surName", foundUser.getSurName())).build()).build();
            }
        } catch (Exception e) {
            response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e).build();
        }
        return response;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getAllUsers() {
        Response response;
        try {
            List<UserDto> foundUsers = userFacade.findAllUsers();

            JsonArray res = Json.createArrayBuilder().build();

            if (foundUsers == null || !foundUsers.isEmpty()) {
                response = Response.status(Response.Status.NOT_FOUND).build();
            } else {
                //TODO: Remove this part of code from this rest endpoint!
                foundUsers.forEach(e -> {
                    res.add(Json.createObjectBuilder().
                            add("id", e.getId()).
                            add("name", e.getName()).
                            add("surName", e.getSurName()).build());
                });
                response = Response.ok().entity(res).build();
            }
        } catch (Exception e) {
            response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getStackTrace()).build();
        }
        return response;
    }

}
