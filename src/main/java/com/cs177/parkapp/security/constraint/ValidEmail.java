package com.cs177.parkapp.security.constraint;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Constraint(validatedBy = ValidEmailValidator.class)
@Target({
    ElementType.TYPE, ElementType.FIELD, ElementType.ANNOTATION_TYPE
})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ValidEmail {
  String message() default "Invalid email";
  Class<?>[] groups() default {};
  Class<? extends Payload>[] payload() default {};
}
