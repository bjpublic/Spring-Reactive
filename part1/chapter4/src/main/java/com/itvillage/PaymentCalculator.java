package com.itvillage;

public class PaymentCalculator {
    private CryptoCurrency.CurrencyUnit unit;
    private int amount;

    public PaymentCalculator(CryptoCurrency.CurrencyUnit unit, int amount) {
        this.unit = unit;
        this.amount = amount;
    }

    public CryptoCurrency.CurrencyUnit getUnit() {
        return unit;
    }

    public int getTotalPayment(int price) {
        return amount * price;
    }
}
