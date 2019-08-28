package com.mgw.ioc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class CarService {

    /**
     * 可以为其注册一个Map
     *
     * */
    @Autowired
    private Map<String,Car> carMap;


    public Map getList() {


        return carMap;
    }

}
