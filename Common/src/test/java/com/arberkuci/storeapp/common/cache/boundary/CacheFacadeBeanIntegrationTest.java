package com.arberkuci.storeapp.common.cache.boundary;


import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.nullValue;

import com.arberkuci.storeapp.common.cache.api.CacheLocal;
import com.arberkuci.storeapp.common.cache.bounday.CacheFacadeBean;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.*;
import org.junit.rules.TestName;
import org.junit.runner.RunWith;

import javax.inject.Inject;
import java.util.logging.Logger;

@RunWith(Arquillian.class)
public class CacheFacadeBeanIntegrationTest extends Assert {

    @Inject
    CacheLocal cacheLocal;

    @Rule
    public TestName methodName = new TestName();

    private static final String className = CacheFacadeBeanIntegrationTest.class.getName();

    private static Logger logger = Logger.getLogger(className);

    @Deployment
    public static JavaArchive createJar() {
        return ShrinkWrap.create(JavaArchive.class, "Common-test.jar").
                addClass(CacheLocal.class).
                addClass(CacheFacadeBean.class);
    }

    @BeforeClass
    public static void initBeforeClass() {
        logger.fine("Starting test -> " + className);
    }

    @AfterClass
    public static void shutDown() {
        logger.fine("Test class -> " + className + " finished");
    }

    @Before
    public void init() {
        logger.entering(className, methodName.getMethodName());
        cacheLocal.clearAllCaches();
    }

    @After
    public void afterTest() {
        logger.exiting(className, methodName.getMethodName());
    }

    @Test
    public void test_Store_SimpleData() {
        String cacheName = "cacheName";
        String key = "key";
        String value = "value";

        cacheLocal.getCache(cacheName).put(key, value);
        String res = (String) cacheLocal.getCache(cacheName).get(key);

        assertThat(res, is(equalTo(value)));
    }

    @Test
    public void test_Update_Stored_Data_In_Cache() {

        String cacheName = "cacheName";
        String key = "key";
        String value = "value";

        cacheLocal.getCache(cacheName).put(key, value);
        String res = (String) cacheLocal.getCache(cacheName).get(key);
        assertThat(res, is(equalTo(value)));

        //update the entry stored in cache.
        value = "another value now";

        cacheLocal.getCache(cacheName).put(key, value);
        res = (String) cacheLocal.getCache(cacheName).get(key);
        assertThat(res, is(equalTo(value)));
    }

    @Test
    public void test_Delete_One_Entry() {
        String key = "key";
        String value = "value";
        String cacheName = "Cache Name";

        //store entry in cache
        cacheLocal.getCache(cacheName).put(key, value);
        //then remove it from cache.
        cacheLocal.getCache(cacheName).remove(key);
        String res = (String) cacheLocal.getCache(cacheName).get(key);
        assertThat(res, is(nullValue()));
    }

    @Test
    public void test_Clear_All_Caches() {

        String key = "key";
        String value = "value";
        String cachName = "cacheName";

        //just put some dummy values in some different caches.
        for (int i = 0; i < 10; i++) {

            cacheLocal.getCache(cachName).put(key, value);

            key += Integer.valueOf(i);
            value += Integer.valueOf(i);
            cachName += Integer.valueOf(i);
        }

        cacheLocal.clearAllCaches();
        assertTrue(cacheLocal.isAnyCacheAvailable() == false);
    }

}
