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

    public static String isCorrectPlayerClass(String newName) {
        Set<ConstraintViolation<Player>> violations = validator.validate(new Player(newName, CustomEnums.HeroClass.Warrior));
        CustomLogger.singleton.printLog(violations.size() + "", 1);
        for (ConstraintViolation<Player> violation : violations) {
            CustomLogger.singleton.printLog(violation.getMessage(), 1);
            return violation.getMessage();
        }
        return "";
    }

    public static String isCorrectPlayerClass(CustomEnums.HeroClass newClass) {
        Set<ConstraintViolation<Player>> violations = validator.validate(new Player("player", newClass));
        CustomLogger.singleton.printLog(violations.size() + "", 1);
        for (ConstraintViolation<Player> violation : violations) {
            CustomLogger.singleton.printLog(violation.getMessage(), 1);
            return violation.getMessage();
        }
        return "";
    }
}
