package hanzy.stake.limbo.hanzimbo.advice;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
@RequiredArgsConstructor
public class CustomControllerAdvice {
    @ExceptionHandler({Exception.class})
    public ResponseEntity<String> handleGlobalException(Exception ex) {
        log.error("An unexpected exception with message {}", ex.getMessage());
        return new ResponseEntity<>("An unexpected exception happened", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler({BindException.class, MissingServletRequestParameterException.class})
    public ResponseEntity<String> handleBadRequestException(Exception ex) {
        log.error(ex.getMessage());
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST.getReasonPhrase(), HttpStatus.BAD_REQUEST);
    }
}
