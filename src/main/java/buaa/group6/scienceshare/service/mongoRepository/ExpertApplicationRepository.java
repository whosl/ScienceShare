package buaa.group6.scienceshare.service.mongoRepository;

import buaa.group6.scienceshare.model.ExpertApplication;
import buaa.group6.scienceshare.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ExpertApplicationRepository extends MongoRepository<ExpertApplication, String> {
    List<ExpertApplication> getByApplyUserNameAndAuthorId(String applyUserName,String authorId);
}
