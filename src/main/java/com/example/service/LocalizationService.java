package com.example.service;

import java.util.List;

public interface LocalizationService {
    String getDirection(String locale);

    List<String> getLocales();

    String getLocaleCode(String locale);

    String getDefaultLocale();
}
