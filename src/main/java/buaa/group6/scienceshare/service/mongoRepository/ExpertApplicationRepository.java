package buaa.group6.scienceshare.service.mongoRepository;

import buaa.group6.scienceshare.model.ExpertApplication;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ExpertApplicationRepository extends MongoRepository<ExpertApplication, String> {

}
