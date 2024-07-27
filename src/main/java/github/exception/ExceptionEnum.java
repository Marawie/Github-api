package github.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ExceptionEnum {

    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "User is not in github database, or you put wrong input"),
    UNKNOWN_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to fetch branches for repository");

    private final HttpStatus httpStatus;
    private final String message;
}