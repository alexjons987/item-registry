package se.alexjons987.item_registry.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class AchievementNotFoundException extends RuntimeException {
    public AchievementNotFoundException(String message) {
        super(message);
    }
}
