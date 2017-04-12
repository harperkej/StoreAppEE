package com.arberkuci.storeapp.web.configuration;

import com.arberkuci.storeapp.web.api.resource.PingRestResource;
import com.arberkuci.storeapp.web.api.user.UserRestResource;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by a.kuci on 4/5/2017.
 */
@ApplicationPath(value = "api")
public class JAXRSConfiguration extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> classes = new HashSet<>();
        classes.add(PingRestResource.class);
        classes.add(UserRestResource.class);
        return classes;
    }
}
