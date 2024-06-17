package com.example.service.impl;

import com.example.models.internal.JsonTree;
import com.example.properties.LocalizationProperties;
import com.example.service.api.LocalizationService;
import com.example.service.api.RequestContextService;
import com.example.utils.JsonMapper;
import com.fasterxml.jackson.core.JsonParseException;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.extern.slf4j.Slf4j;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.Optional.ofNullable;
import static org.apache.commons.lang3.ObjectUtils.isEmpty;

@Slf4j
@ApplicationScoped
public class LocalizationServiceImpl implements LocalizationService {
    private static final String PERIOD = ".";

    private final Map<String, Map<String, String>> localeToDictionaryMap = new HashMap<>();

    @Inject
    RequestContextService requestContextService;

    @Inject
    LocalizationProperties localizationProperties;

    @Inject
    JsonMapper jsonMapper;

    @Override
    public void initiateLocalizationService() {
        log.info("Started reloading locales.");
        performDictionaryReload();
        log.info("Finished reloading locales.");
    }

    @Override
    public String getDirection(String locale) {
        return null;
    }

    @Override
    public List<String> getLocales() {
        return null;
    }

    @Override
    public String getCurrentLocaleCode(String locale) {
        return getLocaleCode(getCurrentLocale());
    }

    public String getLocaleCode(String locale) {
        return locale.substring(0, 2);
    }

    @Override
    public String getDefaultLocale() {
        return localizationProperties.defaultLocale();
    }

    public void performDictionaryReload() {
        Map<String, String> localizationMap = new HashMap<>();

        localizationProperties.locales()
                .forEach((key, localeProperties) -> localizationMap.put(key, localeProperties.path()));

        localizationMap.forEach((key, value) -> {
            JsonTree tree;

            try {
                InputStream input = getClass().getResourceAsStream(value);
                String stub = new String(input.readAllBytes(), StandardCharsets.UTF_8);
                tree = jsonMapper.convertStringToObject(stub, JsonTree.class);
            } catch (JsonParseException e) {
                log.error("Error loading '{}' locale. File '{}' has wrong format", key, value, e);
                return;
            } catch (Exception e) {
                log.error("Error loading '{}' locale. File '{}' does not exist", key, value, e);
                return;
            }

            Map<String, String> keyToLocalizedValueMap = getDictionary(tree);

            localeToDictionaryMap.put(key, keyToLocalizedValueMap);
        });
    }

    @Override
    public String translate(String key) {
        return translate(key, getCurrentLocale());
    }

    @Override
    public String translate(String key, String locale) {
        return ofNullable(localeToDictionaryMap.get(locale))
                .map(dict -> dict.get(key))
                .orElse(key);
    }

    @Override
    public String getCurrentLocale() {
        if (requestContextService.getContext().getLocalization().getCurrentLocale() == null) {
            return getDefaultLocale();
        }
        return requestContextService.getContext().getLocalization().getCurrentLocale();
    }

    private Map<String, String> getDictionary(Map<String, Object> rootNode) {
        Map<String, String> dict = new HashMap<>();
        fillDictionaryRecursively(rootNode, null, dict);
        return dict;
    }

    private void fillDictionaryRecursively(Map<String, Object> node, String keyStartPart, Map<String, String> dict) {
        node.forEach((key, value) -> {
            String newKey = isEmpty(keyStartPart) ? key : keyStartPart.concat(PERIOD).concat(key);
            Class<?> valueClass = value.getClass();
            if (valueClass == String.class) {
                dict.put(newKey, (String) value);
            } else if (Map.class.isAssignableFrom(valueClass)) {
                fillDictionaryRecursively((Map) value, newKey, dict);
            }
        });
    }
}
