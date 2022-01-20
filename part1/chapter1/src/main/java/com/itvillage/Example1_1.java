package com.itvillage;

import java.util.Arrays;
import java.util.List;

public class Example1_1 {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 3, 21, 10, 8, 11);
        int sum = 0;
        for(int number : numbers){
            if(number > 6 && (number % 2 != 0)){
                sum += number;
            }
        }

        System.out.println("합계: " + sum);
    }
}
