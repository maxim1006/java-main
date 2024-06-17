package com.example.utils;


import com.example.constants.RoutingContextConstants;
import com.example.contexts.RequestContext;
import com.example.contexts.RequestContextContent;
import com.example.service.api.RequestContextService;
import io.quarkus.arc.Arc;
import io.quarkus.vertx.http.runtime.CurrentVertxRequest;
import io.vertx.ext.web.RoutingContext;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.Optional;
import java.util.concurrent.Callable;

@ApplicationScoped
public class RequestContextUtils {

    private static final ThreadLocal<RoutingContext> ROUTING_CONTEXT_THREAD_LOCAL = new ThreadLocal<>();

    @Inject
    RequestContextService requestContextService;

    @Inject
    CurrentVertxRequest currentVertxRequest;

    public Optional<RoutingContext> getRoutingContext() {
        RoutingContext customRoutingContext = ROUTING_CONTEXT_THREAD_LOCAL.get();
        if (customRoutingContext != null) {
            return Optional.of(customRoutingContext);
        } else if (Arc.container().requestContext().isActive()) {
            return Optional.ofNullable(currentVertxRequest).map(CurrentVertxRequest::getCurrent);
        } else {
            return Optional.empty();
        }
    }

    public <V> V executeWithRoutingContext(RoutingContext routingContext, Callable<V> task) {
        RequestContextContent requestContent = routingContext.get(RoutingContextConstants.REQUEST_CONTEXT_KEY);
        try {
            ROUTING_CONTEXT_THREAD_LOCAL.set(routingContext);
            // executeWithRequestContext here is required to support context propagation in RequestThreadContextProvider
            return executeWithRequestContext(requestContent, task);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            ROUTING_CONTEXT_THREAD_LOCAL.remove();
        }
    }

    public <V> V executeWithRequestContext(RequestContextContent requestContext, Callable<V> task) {
        RequestContextContent requestContextToRestore = RequestContext.get();

        try {
            RequestContext.set(requestContext);
            return task.call();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            RequestContext.set(requestContextToRestore);
        }
    }

    @SuppressWarnings("UnusedReturnValue")
    public <V> V executeInSessionScope(String sessionId, Callable<V> task) {
        RequestContextContent context = requestContextService.getContext();

        var sessionToRestore = context.getSession().getSessionId();
        var sessionParamsToRestore = context.getSession().getSessionRequestParameters();

        try {
            context.getSession().setSessionId(sessionId);
            context.getSession().setSessionRequestParameters(null);

            return task.call();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            context.getSession().setSessionId(sessionToRestore);
            context.getSession().setSessionRequestParameters(sessionParamsToRestore);
        }
    }
}
