package kalitek.solutions.usermanagement.handler;

import kalitek.solutions.usermanagement.model.Project;
import kalitek.solutions.usermanagement.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;

@Component
public class ProjectHandler extends GenericHandler<Project, Long> {
    @Autowired
    public ProjectHandler(ProjectService service) {
        super(service, "Projet", new ParameterizedTypeReference<>() {});
    }
}
