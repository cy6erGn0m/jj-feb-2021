package org.level.web;

import org.levelp.model.Part;
import org.levelp.model.PartsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@SessionAttributes("user-session")
public class AddPartFormController {
    @Autowired
    private PartsRepository parts;

    @GetMapping("/admin/parts/add")
    public String viewAddForm(
            Model model,
            @ModelAttribute("partForm") AddPartForm partForm,
            BindingResult bindingResult) {
        return "addPart";
    }

    @PostMapping("/admin/parts/add")
    public String add(
            Model model,
            @Valid @ModelAttribute("partForm") AddPartForm partForm,
            BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) {
            return "addPart";
        }

        Part added;
        try {
            added = createPart(partForm);
        } catch (Throwable constraint) {
            bindingResult.addError(new FieldError("form", "partNumber", "partNumber is already used"));
            return "addPart";
        }

        model.addAttribute("itemName", added.getTitle());
        return "added";
    }

    @Secured("ADMIN")
    private Part createPart(AddPartForm partForm) {
        Part added;
        added = parts.saveNewPart(partForm.getPartNumber(), partForm.getPartTitle());
        return added;
    }
}
