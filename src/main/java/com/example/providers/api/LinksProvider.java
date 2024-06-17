package com.example.providers.api;


import com.example.models.api.cms.Link;

import java.util.List;

public interface LinksProvider extends BaseCmsProvider {
    List<Link> getLinks(String locale);
}
