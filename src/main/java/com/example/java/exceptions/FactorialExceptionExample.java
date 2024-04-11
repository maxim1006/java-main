package com.example.java.exceptions;

import com.example.exceptions.FactorialException;

public class FactorialExceptionExample {
    public static void main(String[] args) {
        int res = 1;

        try {
            int factorial = getFactorial(6);
            System.out.println(factorial);
        } catch (FactorialException e) {
            System.out.println("e.getNumber " + e.getNumber());
            System.out.println("e.getMessage " + e.getMessage());
        }
    }

    public static int getFactorial(int num) throws FactorialException {
        int res = 1;

        if (num < 1) throw new FactorialException("The number is less than 1", num);

        for (int i = 1; i <= num; i++) {
            res *= i;
        }

        return res;
    }
}