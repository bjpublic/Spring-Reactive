package com.itvillage;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Supplier 사용 예제
 */
public class Example4_17 {
    public static void main(String[] args) {
        String mnemonic = createMnemonic();
        System.out.println(mnemonic);
    }


    private static String createMnemonic() {
        return Stream
                .generate(() -> getMnemonic())
                .limit(12)
                .collect(Collectors.joining(" "));
    }

    private static String getMnemonic() {
        List<String> mnemonic = Arrays.asList(
                    "alpha", "bravo", "charlie",
                    "delta", "echo", "foxtrot",
                    "golf", "hotel", "india",
                    "juliet", "kilo", "lima",
                    "mike", "november", "oscar",
                    "papa", "quebec", "romeo",
                    "sierra", "tango", "uniform",
                    "victor", "whiskey", "xray",
                    "yankee", "zulu"
                );
        Collections.shuffle(mnemonic);
        return mnemonic.get(0);
    }
}
