package com.mgw.one.chapter2;

public class SimpleCalculatorStrategy implements CalculatorStrategy {

    private static final double SALARY_RATE = 0.1;

    private static final double BOUNS_RATE = 0.1;

    public double calculate(double salary, double bonus) {

        return salary * SALARY_RATE + bonus * BOUNS_RATE;
    }
}
