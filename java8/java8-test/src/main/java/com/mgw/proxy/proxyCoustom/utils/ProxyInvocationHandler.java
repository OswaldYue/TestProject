package com.mgw.proxy.proxyCoustom.utils;

import java.lang.reflect.Method;

public interface ProxyInvocationHandler {


    public Object invoke(Method method);

}
