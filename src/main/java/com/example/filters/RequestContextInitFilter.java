package com.example.filters;

import com.example.constants.PrioritiesConstants;
import com.example.constants.RoutingContextConstants;
import com.example.contexts.RequestContext;
import com.example.contexts.RequestContextContent;
import io.vertx.ext.web.RoutingContext;
import jakarta.annotation.Priority;
import jakarta.inject.Inject;
import jakarta.ws.rs.container.*;
import jakarta.ws.rs.ext.Provider;

import java.io.IOException;

@Provider
@Priority(PrioritiesConstants.REQUEST_CONTEXT_INIT)
@PreMatching
public class RequestContextInitFilter implements ContainerRequestFilter, ContainerResponseFilter {

    @Inject
    RoutingContext routingContext;

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        RequestContextContent requestContextContent = routingContext.get(RoutingContextConstants.REQUEST_CONTEXT_KEY);
        RequestContext.set(requestContextContent);
    }

    @Override
    public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext) throws IOException {
        RequestContext.remove();
    }
}
