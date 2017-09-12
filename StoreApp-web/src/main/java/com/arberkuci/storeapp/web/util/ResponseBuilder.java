package com.arberkuci.storeapp.web.util;


import com.arberkuci.storeapp.common.rest.response.RestResponse;
import com.arberkuci.storeapp.common.rest.response.UserDto;

import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ResponseBuilder {

    public Response buildResponseWithListOfResources(Object data, Request requestType) throws Exception{
        Response response;

        GenericEntity<?  extends List<? extends RestResponse>> genericEntity;
        if (createResponseWithSuccessStatus(data)) {

            if (isList(data)) {
                if(((List<?>)data).get(0) instanceof UserDto){
                    genericEntity = new GenericEntity<List<UserDto>>((List<UserDto>) data) {
                    };
                }else{
                    //Here a custom exception should be thrown
                    throw new Exception("WTF is going on?");
                }
            } else {
                if(data instanceof UserDto){
                    List<UserDto> dataAsList = Arrays.asList((UserDto) data);
                    genericEntity = new GenericEntity<List<UserDto>>(dataAsList) {
                    };
                }else{
                    //Here also a custom exception should be thrown.
                    throw new Exception("WTF is goinf on?");
                }
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
