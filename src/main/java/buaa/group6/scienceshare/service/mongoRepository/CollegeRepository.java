package buaa.group6.scienceshare.service.mongoRepository;

import buaa.group6.scienceshare.model.College;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CollegeRepository extends MongoRepository<College, String> {
    List<College> getAllByNameContaining(String name);
    List<College> getAllByNameContains(String name);
    List<College> getByNameContains(String name);
}
