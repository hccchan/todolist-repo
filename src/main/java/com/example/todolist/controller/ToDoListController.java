package com.example.todolist.controller;

import com.example.todolist.dto.ToDoListDto;
import com.example.todolist.service.ToDoListService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/v1")
public class ToDoListController {

    @Autowired
    private ToDoListService toDoListService;

    @GetMapping("/to-do-list")
    public ResponseEntity<List<ToDoListDto>> findAllToDoList(@RequestParam(defaultValue = "0") int page,
                                                             @RequestParam(defaultValue = "10") int size){
        try{
            //Pageable pageable = PageRequest.of(page, size);
            List<ToDoListDto> toDoListDtos = toDoListService.findAll();
            //List<ToDoListDto> toDoListDtos = toDoListService.findAll(pageable).getContent();
            return toDoListDtos.isEmpty() ? new ResponseEntity<>(HttpStatus.NOT_FOUND) : new ResponseEntity<>(toDoListDtos, HttpStatus.OK);
        } catch (Exception e) {
            log.error("findAllToDoList exception : {}", e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/to-do-list")
    public ResponseEntity<Void> createToDoList(@RequestBody @Valid ToDoListDto toDoListDto){
        try {
            toDoListService.createToDoList(toDoListDto);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            log.error("createToDoList exception : {}", e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/to-do-list")
    public ResponseEntity<ToDoListDto> updateToDoList(@RequestBody @Valid ToDoListDto toDoListDto){
        ToDoListDto toDoListDtoResult = null;
        try {
            toDoListDtoResult = toDoListService.updateToDoList(toDoListDto);
        } catch (Exception e) {
            log.error("updateToDoList exception : {}", e.getMessage());
            e.printStackTrace();
        }
        return toDoListDtoResult != null
                ? new ResponseEntity<>(toDoListDtoResult, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/to-do-list")
    public ResponseEntity<Void> deleteToDoList(@RequestParam Integer id){
        try {
            toDoListService.deleteToDoList(id);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        } catch (Exception e) {
            log.error("deleteToDoList exception : {}", e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
