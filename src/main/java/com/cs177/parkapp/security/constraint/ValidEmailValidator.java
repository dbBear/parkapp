package com.cs177.parkapp.security.constraint;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

public class ValidEmailValidator
  implements ConstraintValidator<ValidEmail, String>
{
  private static final String EMAIL_PATTERN
      = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
      + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

  @Override
  public boolean isValid(
      String email,
      ConstraintValidatorContext context
  ) {
    if(email == null) {
      return false;
    }
    return Pattern.compile(EMAIL_PATTERN).matcher(email).matches();
  }
}
