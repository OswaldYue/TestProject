package com.mgw.one.chapter2;

public class TaxCalculator {


    private final double salary;

    private final double bonus;

    private CalculatorStrategy calculatorStrategy;

    public double getSalary() {
        return salary;
    }

    public double getBonus() {
        return bonus;
    }

    public TaxCalculator(double salary, double bonus) {
        this.salary = salary;
        this.bonus = bonus;
    }

    protected double calcuTax() {

        return calculatorStrategy.calculate(salary,bonus);
    }

    public double calculate() {

        return this.calcuTax();
    }

    public CalculatorStrategy getCalculatorStrategy() {
        return calculatorStrategy;
    }

    public void setCalculatorStrategy(CalculatorStrategy calculatorStrategy) {
        this.calculatorStrategy = calculatorStrategy;
    }
}




