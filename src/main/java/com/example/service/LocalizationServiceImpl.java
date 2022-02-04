package com.example.service;

import com.example.java.enums.EnumExample;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ApplicationScoped
public class LocalizationServiceImpl implements LocalizationService {

    @Inject
    EnumExample enumExample;

    @Override
    public String getDirection(String locale) {
        return null;
    }

    @Override
    public List<String> getLocales() {
        return null;
    }

    @Override
    public String getLocaleCode(String locale) {
        return null;
    }

    @Override
    public String getDefaultLocale() {
        return enumExample.defaultLocale();
    }

    public void performDictionaryReload() {
        Map<String, String> localizationMap = new HashMap<>();

        enumExample.locales()
                .forEach((key, localeProperties) -> localizationMap.put(key, localeProperties.path()));

        System.out.println(localizationMap);

    }
}
