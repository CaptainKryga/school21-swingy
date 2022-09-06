package org.jbashiri.utils;

import org.jbashiri.model.Player;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

public class CustomValidator {
    public static Validator validator;
    public CustomValidator() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    private void check() {
        String playerName = "e";
        String heroClass = "t";
        Player player = new Player(playerName, heroClass);
        Set<ConstraintViolation<Player>> violations = validator.validate(new Player(playerName, heroClass));
        for (ConstraintViolation<Player> violation : violations) {
            CustomLogger.singleton.printLog(violation.getMessage(), 1);
        }
    }
}
