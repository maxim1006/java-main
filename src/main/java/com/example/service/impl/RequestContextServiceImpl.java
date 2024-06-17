package com.example.service.impl;


import com.example.constants.RoutingContextConstants;
import com.example.contexts.RequestContext;
import com.example.contexts.RequestContextContent;
import com.example.service.api.RequestContextService;
import com.example.utils.RequestContextUtils;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class RequestContextServiceImpl implements RequestContextService {

    @Inject
    RequestContextUtils requestContextUtils;

    @Override
    public RequestContextContent getContext() {
        return requestContextUtils.getRoutingContext()
            .map(context -> context.<RequestContextContent>get(RoutingContextConstants.REQUEST_CONTEXT_KEY))
            .orElseGet(() -> {
                RequestContextContent requestContextContent = RequestContext.get();
                if (requestContextContent == null) {
                    throw new IllegalStateException("Request context not defined, maybe @RequestContextControl is missing!");
                }
                return requestContextContent;
            });
    }
}
