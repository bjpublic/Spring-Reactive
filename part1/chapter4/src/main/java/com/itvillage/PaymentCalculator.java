package com.itvillage;

import org.apache.commons.lang3.tuple.Pair;

public class PaymentCalculator {
    private Pair<Integer, Integer> pair;

    public PaymentCalculator(){}

    public PaymentCalculator(Pair<Integer, Integer> pair) {
        this.pair = pair;
    }

    public int getTotalPayment() {
        return pair.getLeft() * pair.getRight();
    }

    public int getTotalPayment(Pair<Integer, Integer> pair) {
        return pair.getLeft() * pair.getRight();
    }
}
