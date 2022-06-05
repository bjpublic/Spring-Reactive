package chapter14;

import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;
import reactor.util.function.Tuples;

import java.time.Duration;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class SampleData {
    public static final List<String> coinNames =
            Arrays.asList("BTC", "ETH", "XRP", "ICX", "EOS", "BCH");

    public static final List<Integer> btcPrices =
            Arrays.asList(50_000_000, 50_100_000, 50_700_000, 51_500_000, 52_000_000);

    public static final List<Tuple2<String, Integer>> coins =
            Arrays.asList(
                    Tuples.of("BTC", 52_000_000),
                    Tuples.of("ETH", 1_720_000),
                    Tuples.of("XRP", 533),
                    Tuples.of("ICX", 2_080),
                    Tuples.of("EOS", 4_020),
                    Tuples.of("BCH", 558_000));

    public static final List<Tuple2<Integer, Long>> btcTopPricesPerYear =
            Arrays.asList(
                    Tuples.of(2010, 565L),
                    Tuples.of(2011, 36_094L),
                    Tuples.of(2012, 17_425L),
                    Tuples.of(2013, 1_405_209L),
                    Tuples.of(2014, 1_237_182L),
                    Tuples.of(2015, 557_603L),
                    Tuples.of(2016, 1_111_811L),
                    Tuples.of(2017, 22_483_583L),
                    Tuples.of(2018, 19_521_543L),
                    Tuples.of(2019, 15_761_568L),
                    Tuples.of(2020, 22_439_002L),
                    Tuples.of(2021, 63_364_000L)
            );

    public static final List<CovidVaccine> coronaVaccineNames = CovidVaccine.toList();

    public static final List<Tuple2<CovidVaccine, Integer>> coronaVaccines =
            Arrays.asList(
                    Tuples.of(CovidVaccine.Pfizer, 1_000_000),
                    Tuples.of(CovidVaccine.AstraZeneca, 3_000_000),
                    Tuples.of(CovidVaccine.Moderna, 4_000_000),
                    Tuples.of(CovidVaccine.Janssen, 2_000_000),
                    Tuples.of(CovidVaccine.Novavax, 2_500_000)
            );
    public static final List<Tuple2<CovidVaccine, Integer>> viralVectorVaccines =
            Arrays.asList(
                    Tuples.of(CovidVaccine.AstraZeneca, 3_000_000),
                    Tuples.of(CovidVaccine.Janssen, 2_000_000)
            );

    public static final List<Tuple2<CovidVaccine, Integer>> mRNAVaccines =
            Arrays.asList(
                    Tuples.of(CovidVaccine.Pfizer, 1_000_000),
                    Tuples.of(CovidVaccine.Moderna, 4_000_000)
            );

    public static final List<Tuple2<CovidVaccine, Integer>> subunitVaccines =
            Arrays.asList(
                    Tuples.of(CovidVaccine.Novavax, 2_500_000)
            );

    public static Map<Integer, Tuple2<Integer, Long>> getBtcTopPricesPerYearMap() {
        return btcTopPricesPerYear
                .stream()
                .collect(Collectors.toMap(t1 -> t1.getT1(), t2 -> t2));
    }

    public static Map<CovidVaccine, Tuple2<CovidVaccine, Integer>> getCovidVaccines() {
        return coronaVaccines
                .stream()
                .collect(Collectors.toMap(t1 -> t1.getT1(), t2 -> t2));
    }

    public static Map<String, Mono<String>> nppMap = new HashMap<>();
    public static Map<String, String> morseCodeMap = new HashMap<>();
    public static String[] morseCodes = {
            ".-","-...","-.-.","-..",".","..-.","--.","....","..",".---","-.-",
            ".-..","--", "-.","---",".--.","--.-",".-.","...","-","..-","...-",
            ".--","-..-","-.--","--.."};

    static {
        nppMap.put("Ontario",
                Mono.just("Ontario Done").delayElement(Duration.ofMillis(1500L)));
        nppMap.put("Vermont",
                Mono.just("Vermont Done").delayElement(Duration.ofMillis(400L)));
        nppMap.put("New Hampshire",
                Mono.just("New Hampshire Done").delayElement(Duration.ofMillis(700L)));
        nppMap.put("New Jersey",
                Mono.just("New Jersey Done").delayElement(Duration.ofMillis(500L)));
        nppMap.put("Ohio",
                Mono.just("Ohio Done").delayElement(Duration.ofMillis(1000L)));
        nppMap.put("Michigan",
                Mono.just("Michigan Done").delayElement(Duration.ofMillis(200L)));
        nppMap.put("Illinois",
                Mono.just("Illinois Done").delayElement(Duration.ofMillis(300L)));
        nppMap.put("Virginia",
                Mono.just("Virginia Done").delayElement(Duration.ofMillis(600L)));
        nppMap.put("North Carolina",
                Mono.just("North Carolina Done").delayElement(Duration.ofMillis(800L)));
        nppMap.put("Georgia",
                Mono.just("Georgia Done").delayElement(Duration.ofMillis(900L)));

        for (char c = 'a'; c <= 'a' + 25; c++) {
            morseCodeMap.put(morseCodes[c - ('z' - 25)], Character.toString(c));
        }
    }

    public static final List<Tuple2<Integer, Integer>> seoulInfected =
            Arrays.asList(
                    Tuples.of(1, 0), Tuples.of(2, 0), Tuples.of(3, 0), Tuples.of(4, 0),
                    Tuples.of(5, 0), Tuples.of(6, 0), Tuples.of(7, 0), Tuples.of(8, 0),
                    Tuples.of(9, 0), Tuples.of(10, 20), Tuples.of(11, 23),
                    Tuples.of(12, 33), Tuples.of(13, 10), Tuples.of(14, 15),
                    Tuples.of(15, 20), Tuples.of(16, 30), Tuples.of(17, 10),
                    Tuples.of(18, 11), Tuples.of(19, 13), Tuples.of(20, 8),
                    Tuples.of(21, 14), Tuples.of(22, 4), Tuples.of(23, 7), Tuples.of(24, 2)
            );

    public static final List<Tuple2<Integer, Integer>> incheonInfected =
            Arrays.asList(
                    Tuples.of(1, 0), Tuples.of(2, 0), Tuples.of(3, 0), Tuples.of(4, 0),
                    Tuples.of(5, 0), Tuples.of(6, 0), Tuples.of(7, 0), Tuples.of(8, 0),
                    Tuples.of(9, 0), Tuples.of(10, 3), Tuples.of(11, 5), Tuples.of(12, 2),
                    Tuples.of(13, 10), Tuples.of(14, 5), Tuples.of(15, 6),
                    Tuples.of(16, 7), Tuples.of(17, 2), Tuples.of(18, 5),
                    Tuples.of(19, 2), Tuples.of(20, 0), Tuples.of(21, 2),
                    Tuples.of(22, 0), Tuples.of(23, 2), Tuples.of(24, 1)
            );

    public static final List<Tuple2<Integer, Integer>> suwonInfected =
            Arrays.asList(
                    Tuples.of(1, 0), Tuples.of(2, 0), Tuples.of(3, 0), Tuples.of(4, 0),
                    Tuples.of(5, 0), Tuples.of(6, 0), Tuples.of(7, 0), Tuples.of(8, 0),
                    Tuples.of(9, 0), Tuples.of(10, 2), Tuples.of(11, 1),
                    Tuples.of(12, 0), Tuples.of(13, 3), Tuples.of(14, 2),
                    Tuples.of(15, 3), Tuples.of(16, 6), Tuples.of(17, 3),
                    Tuples.of(18, 1), Tuples.of(19, 1), Tuples.of(20, 0),
                    Tuples.of(21, 0), Tuples.of(22, 1), Tuples.of(23, 0), Tuples.of(24, 0)
            );

    public static final List<Book> books =
            Arrays.asList(
                    new Book("Advance Java", "Tom",
                            "Tom-boy", 25000, 100),
                    new Book("Advance Python", "Grace",
                            "Grace-girl", 22000, 150),
                    new Book("Advance Reactor", "Smith",
                            "David-boy", 35000, 200),
                    new Book("Getting started Java", "Tom",
                            "Tom-boy", 32000, 230),
                    new Book("Advance Kotlin", "Kevin",
                            "Kevin-boy", 32000, 250),
                    new Book("Advance Javascript", "Mike",
                            "Tom-boy", 32000, 320),
                    new Book("Getting started Kotlin", "Kevin",
                            "Kevin-boy", 32000, 150),
                    new Book("Getting started Python", "Grace",
                            "Grace-girl", 32000, 200),
                    new Book("Getting started Reactor", "Smith",
                            null, 32000, 250),
                    new Book("Getting started Javascript", "Mike",
                            "David-boy", 32000, 330)
            );

    public static final List<Integer> monthlyBookSales2021 =
            Arrays.asList(2_500_000, 3_200_000, 2_300_000, 4_500_000,
                    6_500_000, 5_500_000, 3_100_000, 2_000_000,
                    2_800_000, 4_100_000, 6_200_000, 4_200_000);

    public enum CovidVaccine {
        Pfizer,
        AstraZeneca,
        Moderna,
        Janssen,
        Novavax;

        public static List<CovidVaccine> toList() {
            return Arrays.asList(
                    CovidVaccine.Pfizer,
                    CovidVaccine.AstraZeneca,
                    CovidVaccine.Moderna,
                    CovidVaccine.Janssen,
                    CovidVaccine.Novavax
            );
        }
    }
}
