package kalitek.solutions.usermanagement.handler;

import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.fasterxml.jackson.databind.ObjectMapper;
import kalitek.solutions.usermanagement.model.Identifiable;
import kalitek.solutions.usermanagement.model.dto.GenericResponse;
import kalitek.solutions.usermanagement.model.dto.Request;
import kalitek.solutions.usermanagement.service.GenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import com.fasterxml.jackson.databind.JavaType;

import java.util.Map;

public class GenericHandler<T extends Identifiable<ID>, ID> {
    @Autowired
    private ObjectMapper mapper;
    protected final GenericService<T, ID> service;
    protected final String entityName;
    protected final ParameterizedTypeReference<Request<T>> typeRef;

    public GenericHandler(GenericService<T, ID> service,
                          String entityName,
                          ParameterizedTypeReference<Request<T>> typeRef) {
        this.service = service;
        this.entityName = entityName;
        this.typeRef = typeRef;
    }
    public APIGatewayProxyResponseEvent handle(APIGatewayProxyRequestEvent request) {
        if (request == null || request.getBody() == null || request.getBody().isEmpty()) {
            return new APIGatewayProxyResponseEvent()
                    .withStatusCode(400)
                    .withBody("Requête invalide : corps vide");
        }
        System.err.println(">>> Requête reçue : " + request.getBody());
        try {
            JavaType type = mapper.getTypeFactory().constructType(typeRef.getType());
            Request<T> req = mapper.readValue(request.getBody(), type);
            System.out.println(">>> Action reçue = " + req.action());
            try {
                GenericResponse<T> response = handleAction(req.action(), req.data());
                return new APIGatewayProxyResponseEvent()
                        .withStatusCode(200)
                        .withHeaders(Map.of("Content-Type", "application/json"))
                        .withBody(mapper.writeValueAsString(response));
            } catch (Exception ex) {
                GenericResponse<T> errorResponse = new GenericResponse<>(null, "Erreur : " + ex.getMessage());
                return new APIGatewayProxyResponseEvent()
                        .withStatusCode(400)
                        .withHeaders(Map.of("Content-Type", "application/json"))
                        .withBody(mapper.writeValueAsString(errorResponse));
            }
        } catch (Exception e) {
            return new APIGatewayProxyResponseEvent()
                    .withStatusCode(500)
                    .withBody("Erreur de désérialisation : " + e.getMessage());
        }
    }
    protected GenericResponse<T> handleAction(String action, T data) {
        return switch (action) {
            case "create" -> new GenericResponse<>(service.create(data), entityName + " créé");
            case "read" -> new GenericResponse<>(service.read(data.getId()), entityName + " trouvé");
            case "update" -> new GenericResponse<>(service.update(data), entityName + " mis à jour");
            case "delete" -> {
                service.delete(data.getId());
                yield new GenericResponse<>(null, entityName + " supprimé");
            }
            default -> new GenericResponse<>(null, "Action invalide : " + action);
        };
    }
}
