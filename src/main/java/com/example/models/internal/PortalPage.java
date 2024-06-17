package com.example.models.internal;

import com.example.models.view.PageViewModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(name = "Portal page")
public class PortalPage<T extends PageViewModel> {
    String templateName;
    T data;
}
