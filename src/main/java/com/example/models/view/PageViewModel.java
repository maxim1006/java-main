package com.example.models.view;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

@Data
@NoArgsConstructor
@Schema(name = "Page Base view model")
public class PageViewModel {
    @Schema(required = true, description = "title of the page")
    private String title;
    @Schema(required = true, description = "current locale")
    private String locale;
    @Schema(required = true, description = "direction for current locale")
    private String direction;
    @Schema(required = true, description = "params of the page")
    private String params;
    @Schema(required = true, description = "value for description meta tag for the current page")
    private String description;
}
