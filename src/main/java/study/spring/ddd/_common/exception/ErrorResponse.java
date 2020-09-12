package study.spring.ddd._common.exception;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;

@Data
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ErrorResponse {
    private int rt;
    private String rtMsg;

    // 여러 Status 값이 담겨야 할 경우 사용
    public static ErrorResponse of(HttpStatus httpStatus, String message) {
        return new ErrorResponse(httpStatus.value(), message);
    }

    // @Valid 어노테이션에 의해 예외가 발생할 경우 작동
    public static ErrorResponse of(HttpStatus httpStatus, FieldError fieldError) {
        if (fieldError == null) {
            return new ErrorResponse(httpStatus.value(), "invalid params");
        } else {
            return new ErrorResponse(httpStatus.value(), fieldError.getDefaultMessage());
        }
    }

}
