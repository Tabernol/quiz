package validator;

import exeptions.ValidateException;

public interface MyValidator {
    boolean isValid(boolean result, String message) throws ValidateException;
}
