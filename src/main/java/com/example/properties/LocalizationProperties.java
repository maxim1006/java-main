package com.example.properties;

import io.smallrye.config.ConfigMapping;

import java.util.Map;
import java.util.Optional;

@ConfigMapping(prefix = "localization")
public interface LocalizationProperties {

    Map<String, LocaleProperties> locales();

    String defaultLocale();

    String originalLocale();

    interface LocaleProperties {
        Optional<Direction> direction();

        String path();
    }

    enum Direction {
        RTL("rtl"),
        LTR("ltr");

        Direction(String name) {
            this.name = name;
        }

        private final String name;

        public String getName() {
            return name;
        }
    }
}
