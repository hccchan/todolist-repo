package com.example.todolist.repository;

import com.example.todolist.entity.ToDoList;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class ToDoListRepositoryTest {

    @Autowired
    private ToDoListRepository toDoListRepository;

    @AfterEach
    void tearDown(){
        toDoListRepository.deleteAll();
    }

    @Test
    public void findAll(){
        //given
        ToDoList toDoList = ToDoList.builder()
                .id(1)
                .taskName("cleaning")
                .status(1)
                .build();
        toDoListRepository.save(toDoList);

        //when
        List<ToDoList> toDoLists = toDoListRepository.findAll();

        //then
        assertThat(toDoLists).size().isGreaterThan(0);
    }

    @Test
    public void findById(){
        //given
        ToDoList toDoList = ToDoList.builder()
                .id(1)
                .taskName("homework")
                .status(1)
                .build();
        toDoListRepository.save(toDoList);

        //when
        Optional<ToDoList> toDoListOptional = toDoListRepository.findById(toDoList.getId());

        //then
        assertThat(toDoListOptional).isPresent();
    }
}
