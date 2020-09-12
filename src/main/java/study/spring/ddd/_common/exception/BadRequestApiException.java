package study.spring.ddd._common.exception;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BadRequestApiException extends RuntimeException {
    public BadRequestApiException(String message) {
        super(message);
        log.error(message);
    }
}
