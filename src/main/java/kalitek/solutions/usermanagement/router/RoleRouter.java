package kalitek.solutions.usermanagement.router;

import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import kalitek.solutions.usermanagement.handler.RoleHandler;
import kalitek.solutions.usermanagement.model.dto.GenericResponse;
import kalitek.solutions.usermanagement.model.dto.Request;
import org.springdoc.core.annotations.RouterOperation;
import org.springdoc.core.annotations.RouterOperations;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.reactive.function.server.*;
import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class RoleRouter {
    @RouterOperations({
            @RouterOperation(
                    path = "/roles",
                    method = RequestMethod.POST,
                    beanClass = RoleHandler.class,
                    beanMethod = "handle",
                    operation = @Operation(
                            operationId = "createOrUpdateRole",
                            summary = "Create or Update a Role",
                            requestBody = @RequestBody(content = @Content(schema = @Schema(implementation = Request.class))),
                            responses = {
                                    @ApiResponse(responseCode = "200", description = "Success",
                                            content = @Content(schema = @Schema(implementation = GenericResponse.class)))
                            }
                    )
            )
    })
    public RouterFunction<ServerResponse> roleHandler(RoleHandler roleHandler) {
        return route()
                .POST("/roles", accept(MediaType.APPLICATION_JSON), request ->
                    handleAwsRequest(request, roleHandler))
                .GET("/favicon.ico", req -> ServerResponse.ok().build())
                .build();
    }

    private Mono<ServerResponse> handleAwsRequest(ServerRequest request, RoleHandler roleHandler) {
        return request.bodyToMono(String.class)
            .map(body -> {
                var awsRequest = new APIGatewayProxyRequestEvent();
                awsRequest.setBody(body);
                awsRequest.setHeaders(request.headers().asHttpHeaders().toSingleValueMap());
                awsRequest.setQueryStringParameters(request.queryParams().toSingleValueMap());
                return awsRequest;
            })
            .map(roleHandler::handle)
            .flatMap(awsResponse ->
                ServerResponse
                    .status(awsResponse.getStatusCode())
                    .contentType(MediaType.APPLICATION_JSON)
                    .bodyValue(awsResponse.getBody())
            );
    }
}