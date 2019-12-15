package buaa.group6.scienceshare.service.mongoRepository;

import buaa.group6.scienceshare.model.MagPaper;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface PaperRepository extends MongoRepository<MagPaper, String> {
    MagPaper getAllByTitle(String title);
    MagPaper findBy_id(String _id);
    MagPaper getById(String id);
}
