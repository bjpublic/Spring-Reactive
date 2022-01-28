package com.itvillage;

public class CryptoCurrency {
    private String name;
    private CurrencyUnit unit;
    private int price;

    public CryptoCurrency(String name, CurrencyUnit unit) {
        this.name = name;
        this.unit = unit;
    }

    public CryptoCurrency(String name, CurrencyUnit unit, int price) {
        this.name = name;
        this.unit = unit;
        this.price = price;
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

    public enum CurrencyUnit {
        BTC,
        ETH,
        DOT,
        ADA,
        SOL
    }
}
