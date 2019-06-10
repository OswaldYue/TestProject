package com.mgw.netty.nio;

import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 *  NIO中关于buffer的Scattering与Gathering
 *
 * */
public class TestNio12 {

    public static void main(String[] args) throws Exception{


        BigDecimal decimal = new BigDecimal(5.2);

        BigDecimal multiply = decimal.multiply(new BigDecimal(0.005));
        System.out.println(multiply);

        BigDecimal a = new BigDecimal(0.02600000000000000142941214420488906428435323878439534168113824881530225796577582286772667430341243743896484375);

        DecimalFormat df2 =new DecimalFormat("0.0000000");
        String s = df2.format(a);

        System.out.println(s);

        BigDecimal c = new BigDecimal(s);

        double v = c.setScale(2, BigDecimal.ROUND_UP).doubleValue();
        System.out.println(v);

        System.out.println(new BigDecimal(v));

    }

}
