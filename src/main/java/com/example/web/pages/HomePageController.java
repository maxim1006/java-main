package com.example.web.pages;

import com.example.models.internal.PortalPage;
import com.example.models.view.PageViewModel;
import com.example.utils.PageBuilderUtils;
import io.quarkus.qute.Engine;
import io.quarkus.qute.Template;
import io.quarkus.qute.TemplateInstance;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import java.util.HashMap;

@Tag(name = "Pages")
@Path("/home")
@Produces(MediaType.TEXT_HTML)
public class HomePageController {

    @Inject
    Engine templatesEngine;

    @Inject
    PageBuilderUtils pageBuilderUtils;

    @GET
    @Operation(summary = "Home page")
    @APIResponse(responseCode = "200",
            description = "PageViewModel returned",
            content = @Content(mediaType = MediaType.TEXT_HTML, schema = @Schema(implementation = PageViewModel.class)))
    public TemplateInstance getHomePage(@Context UriInfo uriInfo) {
        PageViewModel model = new PageViewModel();

        pageBuilderUtils.fillCommonParams(
                model,
                "Home page",
                "Home page description",
                new HashMap<>()
        );

        Template template = templatesEngine.getTemplate("home.html");
        return template.data(model);
    }
}
