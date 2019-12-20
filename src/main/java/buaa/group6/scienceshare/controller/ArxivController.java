package buaa.group6.scienceshare.controller;

import buaa.group6.scienceshare.model.Arxiv;
import buaa.group6.scienceshare.service.ArxivService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class ArxivController {
    @Autowired
    ArxivService arxivService;

    @RequestMapping(value = "getArxivByPage", method = RequestMethod.GET)
    Page<Arxiv> getAllByPage(Integer page) {
        return arxivService.getAllByPage(page);
    }
}
