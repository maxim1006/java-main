package com.example.listeners;

import com.example.java.mappers.EtalonEnumMapperTest;
import com.example.utils.StubUtils;
import io.quarkus.runtime.StartupEvent;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Slf4j
@ApplicationScoped
public class StartupApplicationListener {
    @Inject
    StubUtils stubUtils;

    @Inject
    EtalonEnumMapperTest etalonEnumMapperTest;

    private static final Logger LOGGER = LoggerFactory.getLogger(StartupApplicationListener.class);

    void onStart(@Observes StartupEvent ev) {
        log.info("Application started");
        System.out.println("12312312333");
        etalonEnumMapperTest.init();

        try {
            stubUtils.getStubTest();
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
    }
}
