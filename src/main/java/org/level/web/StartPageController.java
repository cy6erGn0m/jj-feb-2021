package org.level.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class StartPageController {
    @GetMapping("/")
    public String index(Model model, @RequestParam(defaultValue = "10") int count) {
        model.addAttribute("title", "Hello, MVC!");
        model.addAttribute("items", loadItems(count));
        return "index";
    }

    private List<Item> loadItems(int count) {
        ArrayList<Item> items = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            items.add(new Item(i, "element " + i));
        }

        return items;
    }
}
