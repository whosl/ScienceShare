package buaa.group6.scienceshare.controller;

import buaa.group6.scienceshare.model.Author;
import buaa.group6.scienceshare.model.MagPaper;
import buaa.group6.scienceshare.service.mongoRepository.PaperRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.data.annotation.Id;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class PaperController {
    @Autowired
    PaperRepository paperRepository;

    @RequestMapping(value = "getPaperByKeyWord", method = RequestMethod.GET)
    public MagPaper getPaperByKeyWord(@RequestParam String title){
        return paperRepository.getAllByTitle(title);
    }

    @RequestMapping(value = "getPaperById", method = RequestMethod.GET)
    public Object getPaperById(@RequestParam String id){
        return paperRepository.getById(id);
    }

    @RequestMapping(value = "getPaper", method = RequestMethod.GET)
    public List<MagPaper> getPaper(){
        return paperRepository.findAll();
    }
}
