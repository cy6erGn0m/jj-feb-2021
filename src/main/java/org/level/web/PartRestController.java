package org.level.web;

import org.levelp.model.Part;
import org.levelp.model.PartsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PartRestController {
    @Autowired
    private PartsRepository repository;

    @GetMapping("/api/part/{id}")
    public Part getPart(@PathVariable int id) {
        return repository.findById(id).get();
    }
}
