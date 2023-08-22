package com.example.todolist.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public enum StatusType {

    NEW(1, "New"),
    COMPLETED(2, "Completed");

    private int type;
    private String desc;
}
