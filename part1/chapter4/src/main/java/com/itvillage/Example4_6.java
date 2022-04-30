package com.itvillage;

import org.apache.commons.lang3.StringUtils;
import java.util.List;

/**
 * ClassName :: static method 형태의 메서드 레퍼런스 예제
 */
public class Example4_6 {
    public static void main(String[] args) {
        List<CryptoCurrency> cryptoCurrencies = SampleData.cryptoCurrencies;

        cryptoCurrencies.stream()
                .map(cc -> cc.getName())
//                .map(name -> StringUtils.upperCase(name))
                .map(StringUtils::upperCase)
                .forEach(name -> System.out.println(name));
    }
}
