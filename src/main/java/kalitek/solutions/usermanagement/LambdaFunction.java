package kalitek.solutions.usermanagement;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.fasterxml.jackson.databind.ObjectMapper;
import kalitek.solutions.usermanagement.handler.UserHandler;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

public class LambdaFunction implements RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final UserHandler userHandler;

    @Autowired
    public LambdaFunction(UserHandler userHandler) {
        this.userHandler = userHandler;
    }

    @Override
    public APIGatewayProxyResponseEvent handleRequest(APIGatewayProxyRequestEvent input, Context context) {
        context.getLogger().log("Received request: " + input.getHttpMethod() + " " + input.getPath());
        try {
            if ("POST".equals(input.getHttpMethod()) && "/users".equals(input.getPath())) {
                return new APIGatewayProxyResponseEvent()
                        .withStatusCode(200)
                        .withBody(objectMapper.writeValueAsString(userHandler.handle(input)))
                        .withHeaders(Map.of("Content-Type", "application/json"));
            }

            return new APIGatewayProxyResponseEvent()
                    .withStatusCode(404)
                    .withBody("Not Found");

        } catch (Exception e) {
            return new APIGatewayProxyResponseEvent()
                    .withStatusCode(500)
                    .withBody("Internal Server Error: " + e.getMessage());
        }
    }
}