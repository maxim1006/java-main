package com.example.java.basics;

public class NaNExamples {
    public static void main(String[] args) {
        double a=0;
        double b=0;
        double c=a/b;

        System.out.println("c    =" + c);
        System.out.println("c+0  =" + (c + 0));

        System.out.println("c<0  =" + (c < 0));
        System.out.println("c>0  =" + (c > 0));

        System.out.println("c==0 =" + (c == 0));
        System.out.println("c!=0 =" + (c != 0));

        System.out.println("c==c =" + (c == c)); // :)
        System.out.println("c!=c =" + (c != c)); // :)

        System.out.println("c == NaN: " + (c == Double.NaN)); // :)))

        System.out.println("c is NaN: " + Double.isNaN(c)); // Делайте именно так
    }
}
