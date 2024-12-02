package com.busanit501.spring_prac;

import com.busanit501.spring_prac.sample.SampleService;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@Log4j2
@ExtendWith(SpringExtension.class) //JUnit5 테스트 설정
//JUnit4 테스트 설정. @Runwith
@ContextConfiguration(locations = "file:src/main/webapp/WEB-INF/root-context.xml")
public class SampleTest {

    @Autowired // 시스템에 등록된 빈을 연결.
    // ApplicationContext (앞에 서버프로그램, 서블릿 컨텍스트와 비슷한 구조.)
    //ApplicationContext : 전역, 공유 공간, 여기에,
    // SampleService 등록해서 , 단위 테스트 하는 파일에서 불러올수 있음.
    // 테스트 파일뿐만 아니라 어느 파일에서도 불러와서 사용가능.
    private SampleService sampleService;

    @Autowired
    private DataSource dataSource;

    @Test
    public void testService1() {
        log.info("testService1: "+ sampleService);
        Assertions.assertNotNull(sampleService);
    }

    @Test
    public void testConnection() throws SQLException {
        Connection connection = dataSource.getConnection();
        log.info("testConnection: "+ connection);
        Assertions.assertNotNull(connection);
        connection.close();
    }
}
