package ru.testRestApi.project.RESTApiLIbraryProject.SecurityService.Exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

import java.util.Date;

@Data
public class AppError {
    private int status;
    private String massage;
    private Date timestamp;

    public AppError(int status, String massage) {
        this.status = status;
        this.massage = massage;
    }
}
