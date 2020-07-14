package com.mirac.ticketing.validator;

/**
 * @author miracy
 */
public interface Validator<T> {
    void validate (T input);
}
