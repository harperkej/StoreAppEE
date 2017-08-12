package com.arberkuci.storeapp.ejb.cache.control;

import javax.ejb.Stateful;

/**
 * Created by harperkej on 5.8.17..
 */
public interface CacheMaintenance {

    public void initCacheMaintenance();

    public void checkCache();

}
