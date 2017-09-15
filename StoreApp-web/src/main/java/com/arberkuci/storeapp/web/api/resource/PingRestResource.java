package com.arberkuci.storeapp.web.api.resource;

import com.arberkuci.storeapp.common.cache.api.CacheLocal;
import com.arberkuci.storeapp.common.rest.response.RestResponse;
import com.arberkuci.storeapp.common.rest.response.UserDto;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.*;
import java.util.concurrent.ConcurrentMap;


@Path("ping")
public class PingRestResource {


    @Inject
    CacheLocal cacheLocal;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response ping() {
        return Response.ok().entity("Everything is ok!").build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("cache")
    public Response getAllCaches(){

        Map<String, Map<Object, Object>> finalRes = new HashMap<>();

        ConcurrentMap<Object, ConcurrentMap<Object, Object>> allCaches = cacheLocal.getAllCaches();
        if(allCaches != null) {

            Set<Object> keys = allCaches.keySet();

            if(keys != null){

                keys.stream().filter(key -> (key != null)).forEach(key -> {

                    ConcurrentMap<Object, Object> oneCache = allCaches.get(key);

                    Map<Object, Object> cacheAsMap = new HashMap<>();

                    if(oneCache != null) {
                        oneCache.forEach((k, v) -> {
                                cacheAsMap.put(k,  v);
                        });
                        finalRes.put((String) key, cacheAsMap);
                    }
                });
            }
        }
        GenericEntity<Map<String, Map<Object, Object>>> res = new GenericEntity<Map<String, Map<Object, Object>>>(finalRes){};
        return Response.ok().entity(res).build();
    }


}
