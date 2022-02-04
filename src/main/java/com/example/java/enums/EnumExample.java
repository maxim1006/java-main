package com.example.java.enums;

import io.smallrye.config.ConfigMapping;

import java.util.Map;
import java.util.Optional;

@ConfigMapping(prefix = "portal")
public interface EnumExample {
    Map<String, LocaleProprties> locales();

    String defaultLocale();

    interface LocaleProprties {
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
