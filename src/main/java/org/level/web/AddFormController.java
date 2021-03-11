package org.level.web;

import org.levelp.model.Part;
import org.levelp.model.PartsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("user-session")
public class AddFormController {
    @Autowired
    private PartsRepository parts;

    @PostMapping("/add")
    @Transactional
    public String add(
            Model model,
            @RequestParam String partNumber,
            @RequestParam String partTitle,
            @ModelAttribute("user-session") UserSession session
    ) {
        if (session.getUserLogin() == null || !session.isAdmin()) {
            throw new RuntimeException("User is not admin");
        }

        Part added = parts.saveNewPart(partNumber, partTitle);

        model.addAttribute("itemName", added.getTitle());
        return "added";
    }
}
