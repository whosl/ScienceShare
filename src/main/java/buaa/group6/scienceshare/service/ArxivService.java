package buaa.group6.scienceshare.service;

import buaa.group6.scienceshare.model.Arxiv;
import org.springframework.data.domain.Page;

public interface ArxivService {
    Page<Arxiv> getAllByPage(Integer page);
}
