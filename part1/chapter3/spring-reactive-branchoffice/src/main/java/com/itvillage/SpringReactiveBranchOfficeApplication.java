package com.itvillage;

import com.itvillage.domain.Book;
import io.netty.util.NettyRuntime;
import io.netty.util.internal.SystemPropertyUtil;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@SpringBootApplication
public class SpringReactiveBranchOfficeApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringReactiveBranchOfficeApplication.class, args);
    }

    @Bean
    public CommandLineRunner init() {
        return (String[] args) -> {
            System.out.println("CPU from Netty: " + NettyRuntime.availableProcessors());
            System.out.println("CPU from JVM: " +  Runtime.getRuntime().availableProcessors());

            int DEFAULT_EVENT_LOOP_THREADS = Math.max(1, SystemPropertyUtil.getInt(
                    "io.netty.eventLoopThreads", NettyRuntime.availableProcessors() * 2));
            System.out.println("DEFAULT_EVENT_LOOP_THREADS: " +  DEFAULT_EVENT_LOOP_THREADS);

            Thread.getAllStackTraces().keySet().stream().collect(Collectors.toList())
                    .forEach(thread -> {
                                System.out.println("-------------------------------------");
                                System.out.println("thread name: " + thread.getName());
                                System.out.println("thread group: " + thread.getThreadGroup().getName());
                                System.out.println("thread ID: " + thread.getId());
                                System.out.println("thread state: " + thread.getState());
                                System.out.println("thread priority: " + thread.getPriority());
                                System.out.println("is daemon: " + thread.isDaemon());
                                System.out.println("-------------------------------------");
                            }
                    );
        };
    }
    @Bean
    public Map<Long, Book> bookMap() {
        Map<Long, Book> bookMap = new HashMap<>();
        for (long i = 1; i <= 2_000_000; i++) {
            bookMap.put(i, new Book(i, "IT Book" + i, 2000));
        }

        return bookMap;
    }

//    @Bean
//    public ReactiveWebServerFactory reactiveWebServerFactory() {
//        NettyReactiveWebServerFactory factory = new NettyReactiveWebServerFactory();
//        factory.addServerCustomizers(builder -> builder.runOn(LoopResources.create("my-http", 16, true)));
//
//        return factory;
//    }
}
