package com.example.listeners;

import com.example.utils.StubUtils;
import io.quarkus.runtime.StartupEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

@ApplicationScoped
public class StartupApplicationListener {
    @Inject
    StubUtils stubUtils;

    private static final Logger LOGGER = LoggerFactory.getLogger(StartupApplicationListener.class);

    void onStart(@Observes StartupEvent ev) {
        try {
            stubUtils.getStubTest();
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
    }
}
