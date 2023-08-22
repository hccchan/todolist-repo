package com.example.todolist.dto;

import com.example.todolist.validation.ValidateStatusType;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ToDoListDto {

    private Integer id;

    private String taskName;

    @ValidateStatusType
    private Integer status;
}
