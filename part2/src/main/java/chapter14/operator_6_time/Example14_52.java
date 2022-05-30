package chapter14.operator_6_time;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuples;

import java.net.URI;
import java.util.Collections;

/**
 * 시간 측정 예제
 *  - elapsed Operator
 *      - emit된 데이터 사이의 경과된 시간을 측정한다.
 *      - emit된 첫번째 데이터는 onSubscribe Signal과 첫번째 데이터 사이의 시간을 기준으로 측정한다.
 *      - 측정된 시간 단위는 milliseconds이다.
 */
@Slf4j
public class Example14_52 {
    public static void main(String[] args) {
        URI worldTimeUri = UriComponentsBuilder.newInstance().scheme("http")
                .host("worldtimeapi.org")
                .port(80)
                .path("/api/timezone/Asia/Seoul")
                .build()
                .encode()
                .toUri();

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));


        Mono.defer(() -> Mono.just(
                            restTemplate
                                    .exchange(worldTimeUri,
                                            HttpMethod.GET,
                                            new HttpEntity<String>(headers),
                                            String.class)
                        )
                )
                .repeat(4)
                .elapsed()
                .map(response -> {
                    DocumentContext jsonContext =
                            JsonPath.parse(response.getT2().getBody());
                    String dateTime = jsonContext.read("$.datetime");
                    return Tuples.of(dateTime, response.getT1());
                })
                .subscribe(
                        data -> log.info("now: {}, elapsed time: {}", data.getT1(), data.getT2()),
                        error -> log.error("# onError:", error),
                        () -> log.info("# onComplete")
                );
    }
}