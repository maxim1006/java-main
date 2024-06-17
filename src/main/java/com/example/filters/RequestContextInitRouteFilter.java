package com.example.filters;

import com.example.constants.RoutingContextConstants;
import com.example.contexts.RequestContextContent;
import io.vertx.ext.web.RoutingContext;
import jakarta.enterprise.context.ApplicationScoped;
import com.example.constants.PrioritiesConstants;
import java.util.UUID;
import io.quarkus.vertx.web.RouteFilter;

// этот фильтры вызовется перед RequestContextInitFilter
@ApplicationScoped
public class RequestContextInitRouteFilter {

    private static final String X_REQUEST_ID_HEADER = "X-Request-ID";

    @RouteFilter(PrioritiesConstants.REQUEST_CONTEXT_INIT_ROUTE_FILTER)
    public void filter(RoutingContext routingContext) {
        String requestId = routingContext.request().getHeader(X_REQUEST_ID_HEADER);
        if (requestId == null) {
            requestId = UUID.randomUUID().toString();
            routingContext.request().headers().set(X_REQUEST_ID_HEADER, requestId);
        }
        routingContext.put(RoutingContextConstants.REQUEST_CONTEXT_KEY, new RequestContextContent(requestId));

        routingContext.next();
    }
}
