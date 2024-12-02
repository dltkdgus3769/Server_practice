package com.busanit501.springex.sample.mapper;

import com.busanit501.springex.mapper.TimeMapper;
import com.busanit501.springex.mapper.TimeMapper2;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@Log4j2
@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations ="file:src/main/webapp/WEB-INF/root-context.xml")
public class TimeMapperTest {
    @Autowired(required = false)
    private TimeMapper2 timeMapper;


    @Test
    public void testGetTime(){
        log.info("getNow:"+timeMapper.getNow());
    }
}
