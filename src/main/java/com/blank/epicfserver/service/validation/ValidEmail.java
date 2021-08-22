package com.blank.epicfserver.service.validation;

import javax.validation.Constraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@NotBlank(message = "Email is blank")
@Size(min=4, max=255, message = "Email is too small or too big")
@Email(message = "Email is invalid")
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy= {})
public @interface ValidEmail {

    String message() default "";

    Class[] groups() default {};

    Class[] payload() default {};
}