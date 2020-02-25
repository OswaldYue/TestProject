package com.mgw.one.chapter2;

public class TaxMain {

    public static void main(String[] args) {

//        TaxCalculator taxCalculator = new TaxCalculator(10000.0, 2000.0) {
//            @Override
//            protected double calcuTax() {
//                return getBonus() * 0.15 + getSalary() * 0.1;
//            }
//        };
//        double tax = taxCalculator.calculate();
//
//        System.out.println(tax);

        TaxCalculator taxCalculator = new TaxCalculator(10000.0, 2000.0);
//        SimpleCalculatorStrategy strategy = new SimpleCalculatorStrategy();

        taxCalculator.setCalculatorStrategy((s,b) -> s * 0.1 + b * 0.15);

        double tax = taxCalculator.calculate();

        System.out.println(tax);


    }

}
