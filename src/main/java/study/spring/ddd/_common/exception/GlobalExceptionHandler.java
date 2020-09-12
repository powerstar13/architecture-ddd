package study.spring.ddd._common.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    /**
     * @param e : BadRequestException 발생 될 경우의 핸들링
     * @return ErrorResponse : { "rt": 400, "rtMsg": "BadRequestException 호출에서 넘겨진 message 내용" }
     */
    @ExceptionHandler(study.spring.ddd._common.exception.BadRequestApiException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleBadRequestApiException(study.spring.ddd._common.exception.BadRequestApiException e) {
        return ErrorResponse.of(HttpStatus.BAD_REQUEST, e.getMessage());
    }

    /**
     * @param e : @Valid 에서 유효성 검사 시 MethodArgumentNotValidException 발생할 경우의 핸들링
     *     - Entity Column --> @NotBlank(message = "...은 필수값 입니다.")
     * @return ErrorResponse : { "rt": 400, "rtMsg": "위에서 정의된 message 속성을 사용한 내용" }
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        return ErrorResponse.of(HttpStatus.BAD_REQUEST, e.getBindingResult().getFieldError());
    }

    /**
     * @param e : RuntimeException 발생할 경우 핸들링
     * @return ErrorResponse : { "rt": 500, "rtMsg": "알 수 없는 서버 오류가 발생하였습니다." }
     */
    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse handleRuntimeException(RuntimeException e) {
        log.error(e.getMessage());
        return ErrorResponse.of(HttpStatus.INTERNAL_SERVER_ERROR, "알 수 없는 서버 오류가 발생하였습니다.");
    }

}
