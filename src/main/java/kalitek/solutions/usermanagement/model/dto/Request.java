package kalitek.solutions.usermanagement.model.dto;

public record Request<T>(String action, T data) {}
