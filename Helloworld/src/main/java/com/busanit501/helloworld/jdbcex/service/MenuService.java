package com.busanit501.helloworld.jdbcex.service;


import com.busanit501.helloworld.jdbcex.dao.MenuDAO;
import com.busanit501.helloworld.jdbcex.dto.MenuDTO;
import com.busanit501.helloworld.jdbcex.dto.TodoDTO;
import com.busanit501.helloworld.jdbcex.util.MapperUtil;
import com.busanit501.helloworld.jdbcex.vo.MenuVO;
import com.busanit501.helloworld.jdbcex.vo.TodoVO;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

@Log4j2
public enum MenuService {
    INSTANCE;

    private MenuDAO menuDAO;
    private ModelMapper modelMapper;

    MenuService(){
        menuDAO = new MenuDAO();
        modelMapper = MapperUtil.INSTANCE.get();
    }

    //1
    // register
    public void register (MenuDTO menuDTO) throws SQLException {
        MenuVO menuVO = modelMapper.map(menuDTO,MenuVO.class);
        log.info("menuVO :"+menuVO);
        menuDAO.insert(menuVO);
    }

    //2
    // 전체 조회
    public List<MenuDTO> listAll() throws SQLException {
        List<MenuVO> voList = menuDAO.selectAll();
        log.info("voList : " + voList);
        List<MenuDTO> dtoList = voList.stream().map(vo -> modelMapper.map(vo, MenuDTO.class))
                .collect(Collectors.toList());
        return dtoList;
    }
    //3
    // 상세보기
    public MenuDTO get(Long tno) throws SQLException {
        log.info("tno : " + tno);
        MenuVO menuVO = menuDAO.selectOne(tno);
        MenuDTO menuDTO = modelMapper.map(menuVO,MenuDTO.class);
        return menuDTO;
    }
    //4
    // 수정하기
    public void update(MenuDTO menuDTO) throws SQLException {
        log.info("menuDTO:"+menuDTO);
        MenuVO menuVO = modelMapper.map(menuDTO,MenuVO.class);
        menuDAO.updateOne(menuVO);
    }
    //5 삭제 기능.
    public void delete(Long tno) throws SQLException {
        menuDAO.deleteMenu(tno);
    }
}
