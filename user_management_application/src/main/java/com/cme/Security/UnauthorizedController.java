package com.cme.Security;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UnauthorizedController {

    @RequestMapping("/unauthorized")
    public String showUnauthorizedPage(Model model) {
        model.addAttribute("errorMessage", "You are not authorized to access this page.");
        return "unauthorized";  // This will resolve to src/main/resources/templates/unauthorized.html
    }
}
