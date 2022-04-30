package chapter14;

import java.util.List;

public class CryptoCurrencyPriceEmitter {
    private CryptoCurrencyPriceListener listener;

    public void setListener(CryptoCurrencyPriceListener listener) {
        this.listener = listener;
    }

    public void flowInto(List<Integer> priceList) {
        listener.onPrice(priceList);
    }
    public void complete() {
        listener.onComplete();
    }
}
