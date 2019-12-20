package buaa.group6.scienceshare.service.mongoRepository;

import buaa.group6.scienceshare.model.Feed;
import buaa.group6.scienceshare.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface UserRepository extends MongoRepository<User, String> {
    List<User> getByEmailAddress(String emailAddress);
    User getByUsername(String username);
    List<User> getByIdentity(int permission);
}
