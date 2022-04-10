package chapter13;

import reactor.core.publisher.Flux;

public class RecordTestExample {
    public static Flux<String> getCapitalizedCountry(Flux<String> source) {
        return source
                .map(country -> country.substring(0, 1).toUpperCase() +
                                country.substring(1));
    }
}
