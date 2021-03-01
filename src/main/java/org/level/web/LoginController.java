package org.level.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@SessionAttributes("user-session")
public class LoginController {
    @GetMapping("/login")
    public String showLoginForm() {
        return "login-form";
    }

    @PostMapping("/login")
    public RedirectView submitLoginForm(
            @RequestParam String userName,
            @RequestParam String password,
            @ModelAttribute("user-session") UserSession session
    ) {
        if (userName.equals("admin") && password.equals("admin")) {
            session.setUserLogin("admin");
            session.setAdmin(true);
            return new RedirectView("/");
        } else {
            return new RedirectView("/login");
        }
    }
}
