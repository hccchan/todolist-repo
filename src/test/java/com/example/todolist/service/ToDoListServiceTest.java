package com.example.todolist.service;

import com.example.todolist.dto.ToDoListDto;
import com.example.todolist.dto.ToDoListDtoMapper;
import com.example.todolist.entity.ToDoList;
import com.example.todolist.repository.ToDoListRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ToDoListServiceTest {

    @Mock
    private ToDoListRepository toDoListRepository;

    @Mock
    private ToDoListDtoMapper toDoListDtoMapper;

    @InjectMocks
    private ToDoListService toDoListService;

    @Test
    void testSaveToDoList() {
        //given
        ToDoListDto toDoListDto = ToDoListDto.builder()
                .id(1)
                .taskName("cleaning")
                .status(1)
                .build();

        ToDoList toDoList = ToDoList.builder()
                .id(toDoListDto.getId())
                .taskName(toDoListDto.getTaskName())
                .status(toDoListDto.getStatus())
                .build();

        //when
        when(toDoListDtoMapper.toDoListDtoToToDoList(any())).thenReturn(toDoList);
        toDoListService.createToDoList(toDoListDto); //service calling toDoListRepository.save()

        ArgumentCaptor<ToDoList> toDoListArgumentCaptor =
                ArgumentCaptor.forClass(ToDoList.class);
        verify(toDoListRepository).save(toDoListArgumentCaptor.capture()); //verify got calling save(), must pass in @Mock instance

        //then
        ToDoList capturedToDoList = toDoListArgumentCaptor.getValue();
        assertThat(capturedToDoList).isEqualTo(toDoList); //compare the captured toDoList against toDoList declaration above

    }

    @Test
    void testUpdateToDoList() {

        ToDoList toDoList = ToDoList.builder()
                .id(1)
                .taskName("cleaning")
                .status(1)
                .build();

        when(toDoListRepository.findById(toDoList.getId())).thenReturn(Optional.of(toDoList));

        ToDoListDto toDoListDto = ToDoListDto.builder()
                .id(1)
                .taskName("cleaning updated")
                .status(1)
                .build();

        ToDoList updatedToDoList = ToDoList.builder()
                .id(toDoListDto.getId())
                .taskName(toDoListDto.getTaskName())
                .status(toDoListDto.getStatus())
                .build();


        when(toDoListDtoMapper.toDoListDtoToToDoList(any())).thenReturn(updatedToDoList);
        toDoListService.updateToDoList(toDoListDto);

        ArgumentCaptor<ToDoList> toDoListUpdateArgumentCaptor =
                ArgumentCaptor.forClass(ToDoList.class);
        verify(toDoListRepository).save(toDoListUpdateArgumentCaptor.capture());

        ToDoList toDoList1 = toDoListUpdateArgumentCaptor.getValue();
        assertThat(toDoList1.getTaskName()).isEqualTo(toDoListDto.getTaskName());

    }

    @Test
    void testDeleteToDoList(){
        //given
        ToDoList toDoList = ToDoList.builder()
                .id(1)
                .taskName("cleaning")
                .status(1)
                .build();

        toDoListService.deleteToDoList(toDoList.getId());

        //then
        //2 way of doing
        verify(toDoListRepository)
                .deleteById(toDoList.getId()); //verify got calling deleteById(), must pass in @Mock instance
        verify(toDoListRepository, times(1))
                .deleteById(toDoList.getId()); //verify got calling deleteById() 1 time, must pass in @Mock instance

    }

}
