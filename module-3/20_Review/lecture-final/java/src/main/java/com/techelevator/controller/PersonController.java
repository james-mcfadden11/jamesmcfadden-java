package com.techelevator.controller;

import com.techelevator.dao.PersonDao;
import com.techelevator.model.Person;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@PreAuthorize("isAuthenticated()")
public class PersonController {
    private final PersonDao dao;

    public PersonController(PersonDao dao) {
        this.dao = dao;
    }

    @RequestMapping(path = "/person/{id}", method = RequestMethod.GET)
    public Person getPerson(@PathVariable int id) {
        return dao.getPerson(id);
    }
}
