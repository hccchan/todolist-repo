package com.example.todolist.controller;

import com.example.todolist.dto.ToDoListDto;
import com.example.todolist.entity.ToDoList;
import com.example.todolist.repository.ToDoListRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
public class ToDoListControllerTest {

    @Autowired
    private ToDoListRepository toDoListRepository;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testFindAll() throws Exception {
        ToDoList toDoList = ToDoList.builder()
                .id(1)
                .taskName("cleaning")
                .status(1)
                .build();

        toDoListRepository.save(toDoList);

        mockMvc.perform(get("/v1/to-do-list"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].id").value(1));

    }

    @Test
    void testCreateToDoList() throws Exception {

        ToDoListDto toDoListDto = ToDoListDto.builder()
                .id(1)
                .taskName("cleaning")
                .status(1)
                .build();

        mockMvc.perform(post("/v1/to-do-list").contentType(MediaType.APPLICATION_JSON).content(convertObjectToJsonBytes(toDoListDto)))
                .andExpect(status().isCreated());

        ToDoList search = toDoListRepository.findById(toDoListDto.getId())
                .orElse(null);
        assertThat(search).isNotNull();
        assertThat(search.getTaskName()).isEqualTo("cleaning");
    }

    @Test
    void testUpdateToDoList() throws Exception{
        ToDoListDto toDoListDto = ToDoListDto.builder()
                .id(1)
                .taskName("cleaning")
                .status(1)
                .build();

        mockMvc.perform(post("/v1/to-do-list").contentType(MediaType.APPLICATION_JSON).content(convertObjectToJsonBytes(toDoListDto)))
                .andExpect(status().isCreated());

        ToDoList search = toDoListRepository.findById(toDoListDto.getId())
                .orElse(null);
        assertThat(search).isNotNull();
        assertThat(search.getTaskName()).isEqualTo("cleaning");

        ToDoListDto toDoListUpdatedDto = ToDoListDto.builder()
                .id(1)
                .taskName("cleaning updated")
                .status(1)
                .build();

        mockMvc.perform(put("/v1/to-do-list").contentType(MediaType.APPLICATION_JSON).content(convertObjectToJsonBytes(toDoListUpdatedDto)))
                .andExpect(status().isOk());

        ToDoList updatedToDoList = toDoListRepository.findById(toDoListDto.getId())
                .orElse(null);
        assertThat(updatedToDoList).isNotNull();
        assertThat(updatedToDoList.getTaskName()).isEqualTo("cleaning updated");

        //if student not found
        ToDoListDto toDoListDtoNotFound = ToDoListDto.builder()
                .id(2)
                .taskName("homework")
                .status(1)
                .build();
        mockMvc.perform(put("/v1/to-do-list").contentType(MediaType.APPLICATION_JSON).content(convertObjectToJsonBytes(toDoListDtoNotFound)))
                .andExpect(status().isNotFound());
    }

    @Test
    void testDeleteDoToList() throws Exception {

        ToDoList toDoList = ToDoList.builder()
                .id(1)
                .taskName("cleaning")
                .status(1)
                .build();

        toDoListRepository.save(toDoList);

        mockMvc.perform(delete("/v1/to-do-list?id=1"))
                .andExpect(status().isAccepted());

        ToDoList search = toDoListRepository.findById(toDoList.getId())
                .orElse(null);
        assertThat(search).isNull();
    }

    private static byte[] convertObjectToJsonBytes(Object object) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsBytes(object);
    }
}
