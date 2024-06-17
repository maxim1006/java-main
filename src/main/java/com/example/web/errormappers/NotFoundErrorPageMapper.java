package com.example.web.errormappers;

import com.example.models.api.error.PortalErrorCode;
import com.example.utils.ErrorMappingUtils;
import com.example.utils.ErrorPageRenderUtils;
import jakarta.inject.Inject;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import lombok.extern.slf4j.Slf4j;

@Provider
@Slf4j
public class NotFoundErrorPageMapper implements ExceptionMapper<NotFoundException> {

    @Inject
    ErrorMappingUtils errorMappingUtils;

    @Context
    HttpServletRequest request;

    @Inject
    ErrorPageRenderUtils errorPageRenderUtils;

    @Override
    public Response toResponse(NotFoundException exception) {
        log.error("Error NotFoundErrorPageMapper toResponse", exception);

        if (errorMappingUtils.shouldNotRenderErrorPage(request)) {
            return errorMappingUtils.prepareErrorResponse(
                Response.Status.NOT_FOUND,
                PortalErrorCode.NOT_FOUND_ERROR
            );
        }

        return errorPageRenderUtils.renderNotFoundPage();
    }
}
