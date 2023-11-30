package com.kulturman.mdd.validation;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

@Getter
@Setter
public class ValidationHelper {
    private BindingResult bindingResult = new BeanPropertyBindingResult(new Object(), "");

    public static ValidationHelper build() {
        return new ValidationHelper();
    }

    public void addError(String field, String errorType) {
        String []errorTypes = {errorType};

        bindingResult.addError(new FieldError("", field, errorType, false, errorTypes, null, errorType));
    }

    public void raiseException() throws MethodArgumentNotValidException {
        if (bindingResult.hasErrors())
            throw new MethodArgumentNotValidException(null, bindingResult);
    }
}
