package com.busanit501.spring_prac.service;

import com.busanit501.spring_prac.domain.TodoVO;
import com.busanit501.spring_prac.dto.PageRequestDTO;
import com.busanit501.spring_prac.dto.PageResponseDTO;
import com.busanit501.spring_prac.dto.TodoDTO;
import com.busanit501.spring_prac.mapper.TodoMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
public class TodoServiceImpl implements TodoService{

    private final TodoMapper todoMapper;
    private final ModelMapper modelMapper;

    @Override
    public void register(TodoDTO todoDTO) {
        TodoVO todoVO = modelMapper.map(todoDTO, TodoVO.class);
        todoMapper.insert(todoVO);

    }

    // mapper , TodoVO 타입 요소로 목록을 받아옴.
    // 화면에 전달하는 타입, TodoDTO 타입으로 변환
    @Override
    public List<TodoDTO> getAll() {
        List<TodoDTO> list = todoMapper.selectAll().stream()
                .map(vo -> modelMapper.map(vo,TodoDTO.class))
                .collect(Collectors.toList());
        return list;
    }

    @Override
    public PageResponseDTO<TodoDTO> getListWithPage(PageRequestDTO pageRequestDTO) {
        int total = todoMapper.getCount(pageRequestDTO);
        List<TodoDTO> dtolist = todoMapper.selectList(pageRequestDTO).stream()
                .map(vo->modelMapper.map(vo,TodoDTO.class))
                .collect(Collectors.toList());

        PageResponseDTO<TodoDTO> pageResponseDTO = PageResponseDTO.<TodoDTO>withAll()
                .total(total)
                .dtolist(dtolist)
                .pageRequestDTO(pageRequestDTO).build();
        
        return pageResponseDTO;
    }

    @Override
    public TodoDTO getOne(Long tno) {
        TodoVO todoVO= todoMapper.selectOne(tno);
        TodoDTO todoDTO = modelMapper.map(todoVO,TodoDTO.class);
        return todoDTO;
    }

    @Override
    public void delete(Long tno) {
        todoMapper.delete(tno);
    }

    @Override
    public void update(TodoDTO todoDTO) {
        TodoVO todoVO = modelMapper.map(todoDTO,TodoVO.class);
        todoMapper.update(todoVO);

    }





}
