package cichecki.kacper.jsonflattener.validators;

import cichecki.kacper.jsonflattener.dto.UserDto;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordMatchesValidator
        implements ConstraintValidator<PasswordMatches, Object> {

   @Override
   public void initialize(PasswordMatches constraintAnnotation) {
   }

   @Override
   public boolean isValid(Object obj, ConstraintValidatorContext context){
      UserDto user = (UserDto) obj;
      return user.getPassword().equals(user.getMatchingPassword());
   }
}