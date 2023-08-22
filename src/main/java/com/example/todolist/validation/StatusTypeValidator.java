package com.example.todolist.validation;

import com.example.todolist.enumeration.StatusType;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StatusTypeValidator implements ConstraintValidator<ValidateStatusType, Integer> {
    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {
        List<Integer> statusTypes =
                Arrays.asList(StatusType.values())
                        .stream()
                        .map(StatusType::getType)
                        .collect(Collectors.toList());
        return statusTypes.contains(value);
    }
}
