package com.example.utils;

import com.example.constants.FrontendConstants;
import com.example.models.view.ErrorPageViewModel;
import com.example.providers.api.LinksProvider;
import com.example.service.api.LocalizationService;
import io.quarkus.qute.Location;
import io.quarkus.qute.Template;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

@ApplicationScoped
@Slf4j
public class ErrorPageRenderUtils {

    public static final String ERROR_500_PAGE_TITLE_LOCALIZATION_KEY = "error.pageTitle_500";
    public static final String ERROR_404_PAGE_TITLE_LOCALIZATION_KEY = "error.pageTitle_404";

    @Inject
    LinksProvider linksProvider;

    @Inject
    protected PageBuilderUtils pageBuilderUtils;

    @Inject
    LocalizationService localizationService;

    @Inject
    @Location("error.html")
    protected Template errorTemplate;

    public Response renderNotFoundPage() {
        int errorCode = Response.Status.NOT_FOUND.getStatusCode();

        ErrorPageViewModel model = new ErrorPageViewModel();

        model.setError(Integer.toString(errorCode));

        Map<String, Object> params = new HashMap<>();
        try {
            // TODO add Pictures
//            params.put(FrontendConstants.PICTURES, picturesProvider.getPictures(PAGES_COMMON_PICTURES_KEYS));
            params.put(FrontendConstants.LINKS, linksProvider.getLinks(localizationService.getCurrentLocale()));
        } catch (Exception e) {
            log.error("Error render not found page fillErrorPageParams", e);
        }

        return Response
            .status(errorCode)
            .header(HttpHeaders.CONTENT_TYPE, MediaType.TEXT_HTML)
            .entity(errorTemplate.data(model))
            .build();
    }

    public Response renderServerErrorPage() {
        return renderServerErrorPage(Response.Status.INTERNAL_SERVER_ERROR);
    }

    public Response renderServerErrorPage(Response.Status status) {
        int errorCode = status.getStatusCode();

        ErrorPageViewModel model = new ErrorPageViewModel();

        model.setError(Integer.toString(errorCode));

        Map<String, Object> params = new HashMap<>();

        try {
            // TODO add Pictures
//            List<PictureViewModel> pictures = picturesProvider.getPictures(PAGES_COMMON_PICTURES_KEYS);
//            params.put(FrontendConstants.PICTURES, pictures);
            params.put(FrontendConstants.LINKS, linksProvider.getLinks(localizationService.getCurrentLocale()));
        } catch (Exception e) {
            log.error("Error render server error page get pictures", e);
        }

        pageBuilderUtils.fillErrorPageParams(model, localizationService.translate(ERROR_500_PAGE_TITLE_LOCALIZATION_KEY), params);

        return Response
            .status(errorCode)
            .entity(errorTemplate.data(model))
            .build();
    }
}
