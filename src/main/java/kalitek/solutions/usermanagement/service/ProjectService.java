package kalitek.solutions.usermanagement.service;

import kalitek.solutions.usermanagement.model.Project;
import kalitek.solutions.usermanagement.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectService extends GenericService<Project, Long> {
    @Autowired
    public ProjectService(ProjectRepository repo) {
        super(repo, "projet");
    }
}
