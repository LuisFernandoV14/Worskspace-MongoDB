package com.ventura.workspacemongodb.resources.exceptions;

import lombok.Setter;
import lombok.Getter;

import java.io.Serial;
import java.io.Serializable;

@Getter @Setter
public class StandardError implements Serializable {

    // Serial number
    @Serial
    private static final long serialVersionUID = 1L;

    // Attributes
    private Long timestamp;
    private Integer status;
    private String message;
    private String error;
    private String path;

    // Constructors
    public StandardError(Long timestamp, Integer status, String message, String error, String path) {
        this.timestamp = timestamp;
        this.status = status;
        this.message = message;
        this.error = error;
        this.path = path;
    }

    public StandardError() {}
}
