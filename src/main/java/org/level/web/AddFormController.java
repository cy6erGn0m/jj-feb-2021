package org.level.web;

import org.hibernate.exception.ConstraintViolationException;
import org.levelp.model.Part;
import org.levelp.model.PartsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@SessionAttributes("user-session")
public class AddFormController {
    @Autowired
    private PartsRepository parts;

    @GetMapping("/add")
    public String add(
            Model model,
            AddPartForm partForm,
            BindingResult bindingResult) {
        return "addPart";
    }

    @PostMapping("/add")
    @Transactional
    public String add(
            Model model,
            @Valid AddPartForm partForm,
            @ModelAttribute("user-session") UserSession session,
            BindingResult bindingResult
    ) {
        if (session.getUserLogin() == null || !session.isAdmin()) {
            throw new RuntimeException("User is not admin");
        }

        if (bindingResult.hasErrors()) {
            return "addPart";
        }

        Part added;
        try {
            added = parts.saveNewPart(partForm.getPartNumber(), partForm.getPartTitle());
        } catch (ConstraintViolationException constraint) {
            bindingResult.addError(new FieldError("form", "partNumber", "partNumber is already used"));
            return "addPart";
        }

        model.addAttribute("itemName", added.getTitle());
        return "added";
    }
}
