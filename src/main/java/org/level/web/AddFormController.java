package org.level.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AddFormController {
    @PostMapping("/add")
    public String add(Model model, @RequestParam String itemName) {
        model.addAttribute("itemName", itemName);
        return "added";
    }
}
