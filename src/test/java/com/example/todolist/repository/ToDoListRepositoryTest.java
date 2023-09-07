package com.example.todolist.repository;

import com.example.todolist.entity.ToDoList;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("test")
public class ToDoListRepositoryTest {

    @Autowired
    private ToDoListRepository toDoListRepository;

    private ToDoList toDoList;
    @BeforeEach
    void createToDoList(){
        toDoList = ToDoList.builder()
                .id(1)
                .taskName("cleaning")
                .status(1)
                .build();
        toDoList = toDoListRepository.save(toDoList);
    }

    @AfterEach
    void tearDown(){
        toDoListRepository.deleteAll();
    }

    @Test
    public void findAll(){
        //given


        //when
        List<ToDoList> toDoLists = toDoListRepository.findAll();

        //then
        assertThat(toDoLists).size().isGreaterThan(0);
    }

    @Test
    public void findById(){
        //given


        //when
        Optional<ToDoList> toDoListOptional = toDoListRepository.findById(toDoList.getId());

        //then
        assertThat(toDoListOptional).isPresent();
    }
}
