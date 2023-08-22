package com.example.todolist.service;

import com.example.todolist.dto.ToDoListDto;
import com.example.todolist.dto.ToDoListDtoMapper;
import com.example.todolist.entity.ToDoList;
import com.example.todolist.repository.ToDoListRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ToDoListService {

    @Autowired
    private ToDoListRepository toDoListRepository;

    @Autowired
    private ToDoListDtoMapper toDoListDtoMapper;

    public List<ToDoListDto> findAll(){
       return toDoListRepository.findAll().stream()
               .map(toDoListDtoMapper::toDoListToToDoListDto)
               .collect(Collectors.toList());
    }

    public void createToDoList(ToDoListDto toDoListDto){
        toDoListRepository.save(toDoListDtoMapper.toDoListDtoToToDoList(toDoListDto));
    }

    public ToDoListDto updateToDoList(ToDoListDto toDoListDto) {
        ToDoList toDoList = toDoListRepository.findById(toDoListDto.getId()).orElse(null);
        if(toDoList == null){
            return null;
        }
        toDoList = toDoListDtoMapper.toDoListDtoToToDoList(toDoListDto);
        return toDoListDtoMapper.toDoListToToDoListDto(toDoListRepository.save(toDoList));
    }

    public void deleteToDoList(Integer id){
       toDoListRepository.deleteById(id);
    }
}
