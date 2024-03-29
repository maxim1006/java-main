package com.example.utils;

import io.quarkus.runtime.Startup;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.extern.slf4j.Slf4j;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicLong;

@Slf4j
@Startup(1)
@ApplicationScoped
public class NumericIdGenerator {

    private static final String ENV_VARIABLE_HOSTNAME = "HOSTNAME";

    private final AtomicLong generator = new AtomicLong();

    @PostConstruct
    void init() {
        long initialValue = getInitialValue();
        log.info("Initial generator value: {}", initialValue);
        this.generator.set(initialValue);
    }

    private long getInitialValue() {
        // structure: <sign: [1]><timestamp: [27]><hash: [16]><sequence: [20]>
        long timestampPart = (System.currentTimeMillis() / 1000) & 0x07FF_FFFF; // 27 bits for seconds (~4 years)
        int hashCodePart = Objects.hashCode(System.getenv(ENV_VARIABLE_HOSTNAME)) & 0xFFFF; //16 bits for pod name hash
        return (timestampPart << 36) + ((long) hashCodePart << 20);
    }

    public long getNext() {
        return generator.getAndIncrement();
    }
}
