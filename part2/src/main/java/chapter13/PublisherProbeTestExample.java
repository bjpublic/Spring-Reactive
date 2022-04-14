package chapter13;

import reactor.core.publisher.Mono;

public class PublisherProbeTestExample {
    public static Mono<String> processTask(Mono<String> main, Mono<String> standby) {
        return main
                .flatMap(massage -> Mono.just(massage))
                .switchIfEmpty(standby);
    }

    public static Mono<String> supplyMainPower() {
        return Mono.empty();
    }

    public static Mono supplyStandbyPower() {
        return Mono.just("# supply Standby Power");
    }
}
