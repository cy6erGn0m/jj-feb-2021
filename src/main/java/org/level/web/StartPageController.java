package org.level.web;

import org.levelp.model.Part;
import org.levelp.model.PartsRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class StartPageController {
    private final PartsRepository parts;

    public StartPageController(PartsRepository parts) {
        this.parts = parts;
    }

    @GetMapping("/")
    public String index(
            Model model,
            @RequestParam(defaultValue = "10") int count,
            Authentication authentication
    ) {
        String title;
        boolean isAdmin = false;
        if (authentication == null) {
            title = "Hello, anonymous!";
        } else {
            title = "Hello, " + authentication.getName() + "!";
            isAdmin = authentication.getAuthorities().contains(
                    new SimpleGrantedAuthority("ROLE_ADMIN")
            );
        }

        model.addAttribute("title", title);
        model.addAttribute("parts", loadParts(count));
        model.addAttribute("isAdmin", isAdmin);
        model.addAttribute("isLoggedIn", authentication != null);

        return "index";
    }

    private List<Part> loadParts(int count) {
        return parts.findAll();
    }
}
