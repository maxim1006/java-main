package com.example.providers.impl;


import com.example.models.api.cms.Link;
import com.example.providers.api.LinksProvider;
import com.example.service.api.LocalizationService;
import io.quarkus.cache.CacheInvalidateAll;
import io.quarkus.cache.CacheResult;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@ApplicationScoped
@Slf4j
public class LinksProviderImpl implements LinksProvider {
    public static final String CACHE_NAME_LINKS = "links";

    @Inject
    LocalizationService localizationService;

    @CacheInvalidateAll(cacheName = CACHE_NAME_LINKS)
    @Override
    public void reload() {
        //empty because cache reset is done using annotation
    }

    @CacheResult(cacheName = CACHE_NAME_LINKS)
    @Override
    public List<Link> getLinks(String locale) {
        return List.of(
                new Link("home", "/home", "Home")
        );
    }

}


