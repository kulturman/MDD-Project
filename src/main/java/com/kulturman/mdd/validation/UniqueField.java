package com.kulturman.mdd.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UniqueFieldValidator.class)
@Documented
public @interface UniqueField {
    String message() default "Field value must be unique";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
    String tableName();
    String columnName();
}

