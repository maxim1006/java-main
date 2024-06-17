package com.example.service.api;

import java.util.List;

public interface LocalizationService {
    void initiateLocalizationService();

    String getDirection(String locale);

    List<String> getLocales();

    String getCurrentLocaleCode(String locale);

    String getLocaleCode(String locale);

    String getDefaultLocale();

    String translate(String key);

    String translate(String key, String locale);

    String getCurrentLocale();
}
