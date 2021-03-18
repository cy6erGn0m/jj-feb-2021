package org.level.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("user-session")
public class LoginController {
    @GetMapping("/login")
    public String showLoginForm() {
        return "login-form";
    }
}
