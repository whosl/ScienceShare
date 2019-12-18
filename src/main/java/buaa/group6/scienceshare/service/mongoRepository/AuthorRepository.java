package buaa.group6.scienceshare.service.mongoRepository;

import buaa.group6.scienceshare.model.Magauthor;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface AuthorRepository extends MongoRepository<Magauthor, String> {
    List<Magauthor> getTop5ByNormalizedNameStartingWith(String name);
}
