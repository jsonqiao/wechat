package com.linju.framework.validator;

import com.linju.framework.model.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * @author jsonqiao
 *
 * @date 2015/6/16
 */
@Service
public class AuthenticationValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return Authentication.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

    }
}
