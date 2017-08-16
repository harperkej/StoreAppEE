package com.arberkuci.storeapp.web.util;


import com.arberkuci.storeapp.ejb.user.dto.UserDto;

import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.Response;
import java.util.Arrays;
import java.util.List;

public class UserResponseBuilder {


    public Response buildResponseWithListOfResources(Object data, Request requestType) {
        Response response;
        GenericEntity<List<UserDto>> genericEntity;
        if (createResponseWithSuccessStatus(data)) {

            if (isList(data)) {
                genericEntity = new GenericEntity<List<UserDto>>((List<UserDto>) data) {
                };
            } else {
                List<UserDto> dataAsList = Arrays.asList((UserDto) data);
                genericEntity = new GenericEntity<List<UserDto>>(dataAsList) {
                };
            }
            switch (requestType) {
                case DELETE:
                    response = Response.status(Response.Status.OK).entity(genericEntity).build();
                    break;
                case GET:
                    response = Response.status(Response.Status.FOUND).entity(genericEntity).build();
                    break;
                case POST:
                    response = Response.status(Response.Status.CREATED).entity(genericEntity).build();
                    break;
                case UPDATE:
                    response = Response.status(Response.Status.OK).entity(genericEntity).build();
                    break;
                default:
                    response = Response.status(Response.Status.EXPECTATION_FAILED).build();
                    break;
            }
        } else {
            switch (requestType) {
                case DELETE:
                case GET:
                case POST:
                case UPDATE:
                    response = Response.status(Response.Status.NOT_FOUND).build();
                    break;
                default:
                    response = Response.status(Response.Status.EXPECTATION_FAILED).build();
            }
        }
        return response;
    }

    private boolean createResponseWithSuccessStatus(Object data) {
        boolean result;
        boolean isList = this.isList(data);
        if (isList) {
            result = data != null && !((List<?>) data).isEmpty();
        } else {
            result = data != null;
        }
        return result;
    }

    private boolean isList(Object data) {
        return data instanceof List<?>;
    }

}
