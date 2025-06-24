package kalitek.solutions.usermanagement.model.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter

public class GenericResponse<T> {
    private T data;
    private String message;

    public GenericResponse() {}
    public GenericResponse(T data) {
        this.data = data;
    }
    public GenericResponse(T data, String message) {
        this.data = data;
        this.message = message;
    }
}