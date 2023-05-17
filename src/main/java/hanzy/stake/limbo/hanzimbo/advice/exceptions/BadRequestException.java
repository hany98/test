package hanzy.stake.limbo.hanzimbo.advice.exceptions;

import lombok.Getter;
import org.springframework.validation.BindingResult;

@Getter
public class BadRequestException extends RuntimeException {

    private final transient BindingResult bindingResult;

    public BadRequestException(String message) {
        super(message);
        bindingResult = null;
    }

    public BadRequestException(BindingResult bindingResult) {
        this.bindingResult = bindingResult;
    }
}
