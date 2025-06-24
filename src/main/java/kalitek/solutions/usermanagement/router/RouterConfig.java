package kalitek.solutions.usermanagement.router;

import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import kalitek.solutions.usermanagement.handler.GenericHandler;
import kalitek.solutions.usermanagement.handler.RoleHandler;
import kalitek.solutions.usermanagement.handler.UserHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.*;
import reactor.core.publisher.Mono;

@Configuration
public class RouterConfig {

    @Bean
    public RouterFunction<ServerResponse> routes(
            UserHandler userHandler
            , RoleHandler roleHandler
            //,GroupHandler groupHandler
            //,PermissionHandler permissionHandler
    ) {
        return RouterFunctions
                .route(RequestPredicates.POST("/users")
                        .and(RequestPredicates.accept(MediaType.APPLICATION_JSON)),
                        request -> handleAwsRequest(request, userHandler))
              .andRoute(RequestPredicates.POST("/roles")
                      .and(RequestPredicates.accept(MediaType.APPLICATION_JSON)),
                      request -> handleAwsRequest(request, roleHandler))
//              .andRoute(RequestPredicates.POST("/groups")
//                      .and(RequestPredicates.accept(MediaType.APPLICATION_JSON)),
//                      request -> handleAwsRequest(request, groupHandler))
//              .andRoute(RequestPredicates.POST("/permissions")
//                      .and(RequestPredicates.accept(MediaType.APPLICATION_JSON)),
//                      request -> handleAwsRequest(request, permissionHandler))
                .andRoute(RequestPredicates.GET("/favicon.ico"),
                        req -> ServerResponse.ok().build());
    }

    private Mono<ServerResponse> handleAwsRequest(ServerRequest request, GenericHandler<?, ?> handler) {
        return request.bodyToMono(String.class)
            .map(body -> {
                var awsRequest = new APIGatewayProxyRequestEvent();
                awsRequest.setBody(body);
                awsRequest.setHeaders(request.headers().asHttpHeaders().toSingleValueMap());
                awsRequest.setQueryStringParameters(request.queryParams().toSingleValueMap());
                return awsRequest;
            })
            .map(handler::handle)
            .flatMap(awsResponse ->
                ServerResponse
                    .status(awsResponse.getStatusCode())
                    .contentType(MediaType.APPLICATION_JSON)
                    .bodyValue(awsResponse.getBody())
            );
    }
}