package buaa.group6.scienceshare.controller;

import buaa.group6.scienceshare.model.Magauthor;
import buaa.group6.scienceshare.service.mongoRepository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class AuthorController{
    @Autowired
    AuthorRepository authorRepository;

    @RequestMapping(value = "getAuthorStartWith", method = RequestMethod.GET)
    public List<Magauthor> getAuthorStartWith(@RequestParam String name){
        return authorRepository.getTop5ByNormalizedNameStartingWith(name);
    }
}
