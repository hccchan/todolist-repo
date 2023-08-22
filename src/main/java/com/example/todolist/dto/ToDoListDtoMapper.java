package com.example.todolist.dto;

import com.example.todolist.entity.ToDoList;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {})
public interface ToDoListDtoMapper {

    @Mapping(target = "id", source = "toDoList.id")
    @Mapping(target = "taskName", source = "toDoList.taskName")
    @Mapping(target = "status", source = "toDoList.status")
    ToDoListDto toDoListToToDoListDto(ToDoList toDoList);

    @Mapping(target = "id", source = "toDoListDto.id")
    @Mapping(target = "taskName", source = "toDoListDto.taskName")
    @Mapping(target = "status", source = "toDoListDto.status")
    ToDoList toDoListDtoToToDoList(ToDoListDto toDoListDto);
}