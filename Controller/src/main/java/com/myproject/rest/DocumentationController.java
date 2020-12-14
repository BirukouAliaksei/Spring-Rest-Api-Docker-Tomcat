package com.myproject.rest;

import io.swagger.annotations.Api;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@Api(
        description = "SwaggerUI is located under /documentation. This mapping redirects the necessary resources for the ui.",
        hidden = true
)
@RequestMapping("/documentation")
public class DocumentationController {

    private static final String DOCUMENTATION_API_DOCS = "/api-docs";
    private static final String SWAGGER_RESOURCES = "/swagger-resources";
    private static final String CONFIGURATION_UI = "/swagger-resources/configuration/ui";
    private static final String CONFIGURATION_SECURITY = "/swagger-resources/configuration/security";

    @RequestMapping(SWAGGER_RESOURCES)
    public RedirectView swaggerResources() {
        return new RedirectView(SWAGGER_RESOURCES, true);
    }

    @RequestMapping(DOCUMENTATION_API_DOCS)
    public RedirectView swaggerApiDocs() {
        return new RedirectView(DOCUMENTATION_API_DOCS, true);
    }

    @RequestMapping(CONFIGURATION_UI)
    public RedirectView swaggerUI() {
        return new RedirectView(CONFIGURATION_UI, true);
    }

    @RequestMapping(CONFIGURATION_SECURITY)
    public RedirectView swaggerConfigSecurity() {
        return new RedirectView(CONFIGURATION_SECURITY, true);
    }
}
