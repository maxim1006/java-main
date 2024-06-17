package com.example.models.view;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

@EqualsAndHashCode(callSuper = true)
@Data
@Schema(name = "Page Error view model")
public class ErrorPageViewModel extends PageViewModel {
    @Schema(required = true, description = "Error message")
    private String error;

    @JsonIgnore
    private String picture;
}
