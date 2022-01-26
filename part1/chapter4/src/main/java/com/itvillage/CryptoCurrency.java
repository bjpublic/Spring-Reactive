package com.itvillage;

import java.util.function.Supplier;

public class CryptoCurrency {
    private String name;
    private CurrencyUnit unit;
    private int price;
    private int amount;

    public CryptoCurrency(String name, CurrencyUnit unit) {
        this.name = name;
        this.unit = unit;
    }

    public CryptoCurrency(String name, CurrencyUnit unit, int price, int amount) {
        this.name = name;
        this.unit = unit;
        this.price = price;
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public CurrencyUnit getUnit() {
        return unit;
    }

    public int getPrice() {
        return price;
    }

    public int getAmount() {
        return amount;
    }

    public int getTotalPayment(int amount) {
        return price * amount;
    }

    public enum CurrencyUnit {
        BTC,
        ETH,
        DOT,
        ADA,
        SOL
    }
}
