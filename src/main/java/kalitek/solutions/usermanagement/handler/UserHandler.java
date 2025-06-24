package kalitek.solutions.usermanagement.handler;

import kalitek.solutions.usermanagement.model.User;
import kalitek.solutions.usermanagement.model.dto.GenericResponse;
import kalitek.solutions.usermanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;


@Component
public class UserHandler extends GenericHandler<User, Long> {

    private final UserService userService;

    @Autowired
    public UserHandler(UserService userService) {
        super(userService, "Utilisateur", new ParameterizedTypeReference<>() {});
        System.err.println(">>> Initialisation de UserHandler");
        this.userService = userService;
    }

    @Override
    protected GenericResponse<User> handleAction(String action, User data) {
        if ("disable".equalsIgnoreCase(action)) {
            User disabled = userService.disable(data);
            return new GenericResponse<>(disabled, "Utilisateur Desactiver");
        }
        return super.handleAction(action, data);
    }
}