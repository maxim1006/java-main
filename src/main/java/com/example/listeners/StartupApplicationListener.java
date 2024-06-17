package com.example.listeners;

import com.example.java.mappers.MapperTest;
import com.example.service.api.LocalizationService;
import com.example.utils.StubUtils;
import com.fasterxml.jackson.core.type.TypeReference;
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
    MapperTest mapperTest;

    @Inject
    LocalizationService localizationService;

    private static final Logger LOGGER = LoggerFactory.getLogger(StartupApplicationListener.class);

    void onStart(@Observes StartupEvent ev) {
        log.info("Application started");
        System.out.println("Example of sout");
//        mapperTest.init();

        localizationService.initiateLocalizationService();

        try {
//            stubUtils.getStubTest();
//            System.out.println(stubUtils.getStubFromJson("", new TypeReference<>() {}).toString());
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
    }
}
