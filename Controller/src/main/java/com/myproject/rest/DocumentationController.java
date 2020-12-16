package com.myproject.rest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/documentation")
public class DocumentationController {

    private static final String DOCUMENTATION_API_DOCS = "/api-docs";

    @RequestMapping(DOCUMENTATION_API_DOCS)
    public RedirectView swaggerApiDocs() {
        return new RedirectView(DOCUMENTATION_API_DOCS, true);
    }

}
