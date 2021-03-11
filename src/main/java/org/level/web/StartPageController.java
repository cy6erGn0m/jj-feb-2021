package org.level.web;

import org.levelp.model.Part;
import org.levelp.model.PartsRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@SessionAttributes("user-session")
public class StartPageController {
    private final PartsRepository parts;

    public StartPageController(PartsRepository parts) {
        this.parts = parts;
    }

    @GetMapping("/")
    public String index(
            Model model,
            @RequestParam(defaultValue = "10") int count,
            @ModelAttribute("user-session") UserSession userSession
    ) {
        String title;
        if (userSession.getUserLogin() == null) {
            title = "Hello, anonymous!";
        } else {
            title = "Hello, " + userSession.getUserLogin() + "!";
        }

        model.addAttribute("title", title);
        model.addAttribute("parts", loadParts(count));
        model.addAttribute("isAdmin", userSession.isAdmin());
        model.addAttribute("isLoggedIn", userSession.getUserLogin() != null);

        return "index";
    }

    private List<Part> loadParts(int count) {
        return parts.findAll();
    }

    @ModelAttribute("user-session")
    public UserSession createUserSession() {
        return new UserSession();
    }
}
