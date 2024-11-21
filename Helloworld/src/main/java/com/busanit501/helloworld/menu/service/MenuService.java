package com.busanit501.helloworld.menu.service;

import com.busanit501.helloworld.menu.dto.MenuDTO;
import com.busanit501.helloworld.todo.dto.TodoDTO;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public enum MenuService {
    INSTANCE;

    public void register(MenuDTO menuDto){
        System.out.println("메뉴 기능");
    }
    public List<MenuDTO> getList(){
        List<MenuDTO> menuList = IntStream.range(0,10).mapToObj(
                i-> {
                    MenuDTO menuDTO = new MenuDTO();
                    menuDTO.setMenuName("메뉴 "+i);
                    menuDTO.setPrice((long)i*1000);
                    menuDTO.setDescription("메뉴설명:"+i);
                    return menuDTO;
                }).collect(Collectors.toList());
        return menuList;
    }
    public MenuDTO getone(String tno){
        MenuDTO menuDTO =new MenuDTO();
        menuDTO.setMenuName("국밥");
        menuDTO.setPrice(5000L);
        menuDTO.setDescription("국밥입니다.");

        return menuDTO;
    }
}
