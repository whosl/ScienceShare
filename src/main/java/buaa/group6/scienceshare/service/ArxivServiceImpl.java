package buaa.group6.scienceshare.service;

import buaa.group6.scienceshare.model.Arxiv;
import buaa.group6.scienceshare.service.mongoRepository.ArxivRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@Primary
public class ArxivServiceImpl implements ArxivService {
    @Autowired
    ArxivRepository arxivRepository;

    public Page<Arxiv> getAllByPage(Integer page) {
        PageRequest pageable = PageRequest.of(page, 10, Sort.Direction.DESC, "updated");
        return arxivRepository.findAll(pageable);
    }
}
