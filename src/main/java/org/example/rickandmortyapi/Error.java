package org.example.rickandmortyapi;

public enum Error {

    RESOURCE_NOT_FOUND(404,"Resource not found"),
    INVALID_INPUT(400, "Invalid input"),
    UNEXPECTED_ERROR(500, "Unexpected error");

    private final int code;
    private final String description;

    Error(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

}
