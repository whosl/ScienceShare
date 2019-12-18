package buaa.group6.scienceshare.service;

import buaa.group6.scienceshare.model.ExpertApplication;

import java.util.List;

public interface ExpertApplicationService {
    void updateApplication(ExpertApplication expertApplication);
    List<ExpertApplication> allApplication();
}
