package chapter11;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;
import reactor.util.context.Context;

/**
 * Context API 사용 예제
 */
@Slf4j
public class Example11_3 {
    public static void main(String[] args) throws InterruptedException {
        final String key1 = "company";
        final String key2 = "firstName";
        final String key3 = "lastName";

        Mono
            .deferContextual(ctx ->
                    Mono.just(ctx.get(key1) + ", " + ctx.get(key2) + " " + ctx.get(key3))
            )
            .publishOn(Schedulers.parallel())
            .contextWrite(context ->
                    context.putAll(Context.of(key2, "Steve", key3, "Jobs").readOnly())
            )
            .contextWrite(context -> context.put(key1, "Apple"))
            .subscribe(data -> log.info("# onNext: {}" , data));

        Thread.sleep(100L);
    }
}
