package com.example.utils;

import com.example.constants.UrlConstants;
import com.example.models.api.error.PortalErrorCode;
import com.example.models.api.error.PortalErrorDto;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.ws.rs.core.Response;

import java.util.Optional;

@ApplicationScoped
public class ErrorMappingUtils {

    public boolean shouldNotRenderErrorPage(HttpServletRequest request) {
        return Optional.ofNullable(request)
            .map(HttpServletRequest::getRequestURI)
            .map(uri -> uri.startsWith(UrlConstants.API))
            .orElse(false);
    }

    public PortalErrorDto buildPortalError(PortalErrorCode code, Object payload) {
        return PortalErrorDto
            .builder()
            .code(code.getCode())
            .message(code.getMessage())
            .payload(payload)
            .build();
    }

    public PortalErrorDto buildPortalError(PortalErrorCode code) {
        return buildPortalError(code, null);
    }

    public Response prepareErrorResponse(Response.Status status, PortalErrorCode code, Object payload) {
        return Response
            .status(status)
            .entity(buildPortalError(code, payload))
            .build();
    }

    public Response prepareErrorResponse(Response.Status status, PortalErrorCode code) {
        return Response
            .status(status)
            .entity(buildPortalError(code))
            .build();
    }
}
