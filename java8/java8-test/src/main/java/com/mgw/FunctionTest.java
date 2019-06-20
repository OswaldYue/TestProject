package com.mgw;

import java.io.FileInputStream;
import java.util.function.Function;

public class FunctionTest {


    public static void main(String[] args) throws Exception{

        FunctionTest test = new FunctionTest();

        System.out.println(test.compute(1,value -> { return 2 * value;}));
        System.out.println(test.compute(2,value -> value * value));

        System.out.println(test.convent(3,value -> { return value + "aaaaaa"; }));


        System.out.println(test.compose(2,value-> 3 * value,value -> value * value));
        System.out.println(test.andThen(2,value -> 3 * value, value -> value * value));


        FileInputStream fileInputStream = new FileInputStream("");



    }


    public int compute(int a, Function<Integer,Integer> function) {
        return function.apply(a);
    }

    public String convent(int a,Function<Integer,String> function) {

        return function.apply(a);

    }


    public int compose(int a ,Function<Integer,Integer> function1,Function<Integer,Integer> function2) {

        return function1.compose(function2).apply(a);
    }

    public int andThen(int a ,Function<Integer,Integer> function1,Function<Integer,Integer> function2) {


        return function1.andThen(function2).apply(a);
    }


}
