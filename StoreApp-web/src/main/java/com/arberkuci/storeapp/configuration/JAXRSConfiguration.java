package com.arberkuci.storeapp.configuration;

import com.arberkuci.storeapp.api.resource.PingRestResource;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by a.kuci on 4/5/2017.
 */
@ApplicationPath("api")
public class JAXRSConfiguration extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> classes = new HashSet<>();
        classes.add(PingRestResource.class);
        return super.getClasses();
    }
}
