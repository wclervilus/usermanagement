package kalitek.solutions.usermanagement.handler;

import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.fasterxml.jackson.databind.ObjectMapper;
import kalitek.solutions.usermanagement.model.User;
import kalitek.solutions.usermanagement.service.AuthService;
import kalitek.solutions.usermanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class AuthHandler {
    private final UserService userService;
    private final AuthService authService;
    private final ObjectMapper mapper = new ObjectMapper();

    @Autowired
    public AuthHandler(UserService userService, AuthService authService) {
        this.userService = userService;
        this.authService = authService;
    }

    public APIGatewayProxyResponseEvent login(APIGatewayProxyRequestEvent request) {
        try {
            User payload = mapper.readValue(request.getBody(), User.class);
            User user = userService.read(payload.getId());
            String token = authService.generateToken(user.getId(), 1L);
            return new APIGatewayProxyResponseEvent()
                    .withStatusCode(200)
                    .withHeaders(Map.of("Content-Type", "application/json"))
                    .withBody("{\"token\":\"" + token + "\"}");
        } catch (Exception e) {
            return new APIGatewayProxyResponseEvent()
                    .withStatusCode(400)
                    .withBody("Erreur: " + e.getMessage());
        }
    }
}
