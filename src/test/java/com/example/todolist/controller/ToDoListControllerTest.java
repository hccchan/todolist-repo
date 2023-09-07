package com.example.todolist.controller;

import com.example.todolist.dto.ToDoListDto;
import com.example.todolist.entity.ToDoList;
import com.example.todolist.repository.ToDoListRepository;
import com.example.todolist.service.ToDoListService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import javax.transaction.Transactional;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
@ActiveProfiles("test")
public class ToDoListControllerTest {

    @Autowired
    private ToDoListRepository toDoListRepository;

    @Autowired
    private MockMvc mockMvc;

    private ToDoList toDoList = null;

    private ToDoListDto toDoListDto = null;

    private ToDoListDto updatedToDoListDto = null;

    private ToDoListDto toDoListDtoNotFound = null;

    @MockBean
    ToDoListService toDoListService;
    @BeforeEach
    void init(){
        toDoList = ToDoList.builder()
                .id(1)
                .taskName("cleaning")
                .status(1)
                .build();
        toDoList = toDoListRepository.save(toDoList);

        toDoListDto = ToDoListDto.builder()
                .id(1)
                .taskName("cleaning")
                .status(1)
                .build();

        updatedToDoListDto = ToDoListDto.builder()
                .id(1)
                .taskName("cleaning updated")
                .status(1)
                .build();

        toDoListDtoNotFound = ToDoListDto.builder()
                .id(2)
                .taskName("homework")
                .status(1)
                .build();
    }

    @Test
    void testFindAll() throws Exception {


        given(this.toDoListService.findAll())
                .willReturn(List.of(toDoListDto));

        mockMvc.perform(get("/v1/to-do-list"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].id").value(1));

    }

    @Test
    void testCreateToDoList() throws Exception {



        mockMvc.perform(post("/v1/to-do-list").contentType(MediaType.APPLICATION_JSON).content(convertObjectToJsonBytes(toDoListDto)))
                .andExpect(status().isCreated());


    }

    @Test
    @Transactional
    void testUpdateToDoList() throws Exception{

        given(this.toDoListService.updateToDoList(updatedToDoListDto))
                .willReturn(updatedToDoListDto);

        mockMvc.perform(put("/v1/to-do-list").characterEncoding(StandardCharsets.UTF_8).contentType(MediaType.APPLICATION_JSON).content(convertObjectToJsonBytes(updatedToDoListDto)))
                .andExpect(status().isOk());

        //if student not found
        mockMvc.perform(put("/v1/to-do-list").contentType(MediaType.APPLICATION_JSON).content(convertObjectToJsonBytes(toDoListDtoNotFound)))
                .andExpect(status().isNotFound());
    }

    @Test
    void testDeleteDoToList() throws Exception {


        mockMvc.perform(delete("/v1/to-do-list?id=1"))
                .andExpect(status().isAccepted());

    }

    private static byte[] convertObjectToJsonBytes(Object object) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsBytes(object);
    }
}
