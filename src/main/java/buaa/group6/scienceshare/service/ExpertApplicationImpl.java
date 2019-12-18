package buaa.group6.scienceshare.service;

import buaa.group6.scienceshare.model.ExpertApplication;
import buaa.group6.scienceshare.service.mongoRepository.ExpertApplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Primary
public class ExpertApplicationImpl implements ExpertApplicationService{
    @Autowired
    ExpertApplicationRepository applicationRepository;
    @Override
    public void updateApplication(ExpertApplication expertApplication) {
        applicationRepository.save(expertApplication);
    }

    @Override
    public List<ExpertApplication> allApplication() {
        return applicationRepository.findAll();
    }
}
