package com.arberkuci.storeapp.web.api.user;

import com.arberkuci.storeapp.ejb.user.api.UserFacade;
import com.arberkuci.storeapp.ejb.user.dto.UserDto;
import com.arberkuci.storeapp.web.util.Request;
import com.arberkuci.storeapp.web.util.UserResponseBuilder;

import javax.inject.Inject;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.logging.Logger;

@Path(value = "users")
public class UserRestResource {

    @Inject
    UserFacade userFacade;

    @Inject
    UserResponseBuilder userResponseBuilder;

    private static final String className = UserRestResource.class.getName();

    private Logger logger = Logger.getLogger(className);

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response storeUser(UserDto userDto) {
        final String MN = "storeUser";
        logger.entering(className, MN);
        Response response;
        try {
            UserDto storedUser = userFacade.storeUser(userDto);
            response = userResponseBuilder.buildResponseWithListOfResources(storedUser, Request.POST);
        } catch (Exception e) {
            logger.severe("An error occured while storing the user. The exception is -> " + e.getMessage());
            response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getStackTrace()).build();
        }
        logger.fine("Final response -> " + response);
        logger.exiting(className, MN);
        return response;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public Response findUserById(@PathParam("id") Long id) {
        final String MN = "findUserById";
        logger.entering(className, MN);
        Response response;
        try {
            UserDto foundUser = userFacade.findByUserById(id);
            response = this.userResponseBuilder.buildResponseWithListOfResources(foundUser, Request.GET);
        } catch (Exception e) {
            logger.severe("An error occured while searching the user with id " + id + ". The message is -> " + e.getMessage());
            response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
        logger.fine("Final response -> " + response);
        logger.exiting(className, MN);
        return response;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getAllUsers() {
        final String MN = "getAllUsers";
        logger.entering(className, MN);
        Response response;
        try {
            List<UserDto> foundUsers = userFacade.findAllUsers();
            response = this.userResponseBuilder.buildResponseWithListOfResources(foundUsers, Request.GET);
        } catch (Exception e) {
            logger.fine("An error occurred while fetching all the users. The message -> " + e.getMessage());
            response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
        logger.fine("Final response -> " + response);
        logger.exiting(className, MN);
        return response;
    }

}
