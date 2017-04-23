package com.arberkuci.storeapp.common.logging.facade;


import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;


@Interceptor
public class LoggingInterceptor {


    @AroundInvoke
    public Object interceptMethodInvocation(InvocationContext invocationContext) throws Exception {

        final String methodName = invocationContext.getMethod().getName();
        final String className = invocationContext.getTarget().getClass().getName();

        Logger logger = Logger.getLogger(className);

        final long timeBeforeInvoke;
        final long timeAfterInvoke;
        final long duration;
        logger.entering(className, methodName);
        timeBeforeInvoke = System.currentTimeMillis();

        try {
            return invocationContext.proceed();
        } finally {
            timeAfterInvoke = System.currentTimeMillis();
            duration = timeAfterInvoke - timeBeforeInvoke;
            logger.fine("Method took -> " + duration + " millis to be executed!");
            logger.exiting(className, methodName);
        }
    }

}
