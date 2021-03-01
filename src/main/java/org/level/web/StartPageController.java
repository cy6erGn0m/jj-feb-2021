package org.level.web;

import org.levelp.model.Part;
import org.levelp.model.PartsDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class StartPageController {
    private final PartsDAO parts;

    public StartPageController(PartsDAO parts) {
        this.parts = parts;
    }

    @GetMapping("/")
    public String index(Model model, @RequestParam(defaultValue = "10") int count) {
        model.addAttribute("title", "Hello, MVC!");
        model.addAttribute("parts", loadParts(count));
        return "index";
    }

    private List<Part> loadParts(int count) {
        return parts.findAll();
    }
}
