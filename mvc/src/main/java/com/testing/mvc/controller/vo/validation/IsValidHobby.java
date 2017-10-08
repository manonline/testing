package com.testing.mvc.controller.vo.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = HobbyValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface IsValidHobby {
    String message() default "Please provide a valid Hobby: " + "accepted hobbies are - Music, Football and Hockey";

    String listOfValidHobbies() default "Music";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
