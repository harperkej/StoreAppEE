package com.arberkuci.storeapp.common.logging.facade;


import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import java.util.logging.Logger;


@Interceptor
public class LoggingInterceptor {


    @AroundInvoke
    public Object interceptMethodInvocation(InvocationContext invocationContext) throws Exception {

        final String methodName = invocationContext.getMethod().getName();
        final String className = invocationContext.getTarget().getClass().getName();

        Logger logger = Logger.getLogger(className);

        final long timeBeforeMethodInvocation;
        final long timeAfterMethodInvocation;
        final long millis;
        logger.entering(className, methodName);
        timeBeforeMethodInvocation = System.currentTimeMillis();

        try {
            return invocationContext.proceed();
        } finally {
            timeAfterMethodInvocation = System.currentTimeMillis();
            millis = timeAfterMethodInvocation - timeBeforeMethodInvocation;
            logger.fine("Method took -> " + millis + " millis to be executed!");
            logger.exiting(className, methodName);
        }
    }

}
