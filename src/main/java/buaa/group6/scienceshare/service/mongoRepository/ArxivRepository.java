package buaa.group6.scienceshare.service.mongoRepository;

import buaa.group6.scienceshare.model.Arxiv;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ArxivRepository extends PagingAndSortingRepository<Arxiv, String> {
    Page<Arxiv> findAll(Pageable pageable);
}
