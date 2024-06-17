package com.example.utils;


import com.example.models.view.PageViewModel;
import com.example.service.api.LocalizationService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

@ApplicationScoped
@Slf4j
public class PageBuilderUtils {
    public static final String DEFAULT_PAGE_TITLE_KEY = "Default page title";

    @Inject
    LocalizationService localizationService;

    @Inject
    JsonMapper jsonMapper;

    public void fillErrorPageParams(PageViewModel model, String title, Map<String, Object> params) {
        String currentLocale = localizationService.getCurrentLocale();
        String currentLocaleCode = localizationService.getLocaleCode(currentLocale);

        model.setLocale(currentLocaleCode);
        model.setDirection(localizationService.getDirection(currentLocale));
        model.setTitle(title);

        Map<String, Object> pageParams = new HashMap<>();

        if (params != null) {
            pageParams.putAll(params);
        }
    }

    public void fillCommonParams(PageViewModel model, String title, String description, Map<String, Object> params) {
        String currentLocale = localizationService.getCurrentLocale();
        String currentLocaleCode = localizationService.getLocaleCode(currentLocale);

        model.setLocale(currentLocaleCode);
        model.setDirection(localizationService.getDirection(currentLocale));
        model.setTitle(
                localizationService.translate(title, currentLocale) == null ?
                        localizationService.translate(DEFAULT_PAGE_TITLE_KEY, currentLocale) :
                        localizationService.translate(title, currentLocale)
        );

        model.setDescription(
                localizationService.translate(description, currentLocale)
        );

        model.setParams(getPageParams(params));
    }

    private String getPageParams(Map<String, Object> params) {
        Map<String, Object> defaultParams = new HashMap<>();
        Map<String, Object> resultMap = new HashMap<>(defaultParams);
        if (params != null) {
            resultMap.putAll(params);
        }

        return jsonMapper.convertObjectToString(resultMap);
    }
}
