package org.level.web;

import org.levelp.model.Part;
import org.levelp.model.PartsDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.persistence.EntityManager;

@Controller
public class AddFormController {
    @Autowired
    private PartsDAO parts;

    @Autowired
    private EntityManager manager;

    @PostMapping("/add")
    public String add(
            Model model,
            @RequestParam String partNumber,
            @RequestParam String partTitle
    ) {
        Part added;
        manager.getTransaction().begin();
        try {
            added = parts.saveNewPart(partNumber, partTitle);
            manager.getTransaction().commit();
        } finally {
            if (manager.getTransaction().isActive()) {
                manager.getTransaction().rollback();
            }
        }

        model.addAttribute("itemName", added.getTitle());
        return "added";
    }
}
