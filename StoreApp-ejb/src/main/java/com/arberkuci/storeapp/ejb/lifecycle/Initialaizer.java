package com.arberkuci.storeapp.ejb.lifecycle;

import com.arberkuci.storeapp.common.lifecycle.ApplicationStartup;
import com.arberkuci.storeapp.ejb.cache.control.CacheMaintenance;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;
import java.util.logging.Logger;

/**
 * Created by harperkej on 4.8.17..
 */
@Singleton
@Startup
public class Initialaizer implements ApplicationStartup{

    private static final String className = Initialaizer.class.getName();


    private Logger logger = Logger.getLogger(className);

    @Inject
    CacheMaintenance cacheMaintenance;

    @PostConstruct
    @Override
    public void startApp() {

        cacheMaintenance.initCacheMaintenance();

    }
}
