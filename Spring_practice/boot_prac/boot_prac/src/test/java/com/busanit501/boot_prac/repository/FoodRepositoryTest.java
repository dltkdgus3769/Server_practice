package com.busanit501.boot_prac.repository;

import com.busanit501.boot_prac.domain.Food;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest
@Log4j2
public class FoodRepositoryTest {

    @Autowired
    private FoodRepository foodRepository;

    @Test
    public void testInsert() {
        IntStream.range(1,100).forEach(i->{
            Food food = Food.builder()
                    .title("샘플 제목:"+i)
                    .content("샘플 내용:"+i)
                    .writer("샘플 작성자:"+i).build();
           Food result = foodRepository.save(food);
           log.info("추가된 fno 번호 : "+result);
        });
    }

    @Test
    public void testSelectOne(){
        Long fno = 99L;
        Optional<Food> result = foodRepository.findById(fno);
        Food food = result.orElseThrow();
        log.info("하나 조회:"+food);
    }

    @Test
    public void testSelectAll(){
        List<Food> result = foodRepository.findAll();
        for (Food food : result) {
            log.info(food);
        }
    }

    @Test
    public void testUpdate(){
        Long fno = 99L;
        Optional<Food> result = foodRepository.findById(fno);
        Food food = result.orElseThrow();
        food.changeTitleContent("변경제목","변경내용");
        foodRepository.save(food);
    }

    @Test
    public void testDelete(){
        Long fno = 99L;
        foodRepository.deleteById(fno);

    }

    @Test
    public void testPaging(){
        Pageable pageable = PageRequest.of(0, 10, Sort.by("fno").descending());
        Page<Food> result = foodRepository.findAll(pageable);
        log.info("총 개수:"+result.getTotalElements());
        log.info("10개씩 나눠진 데이터 총페이지:"+result.getTotalPages());
        log.info("페이징 된 결과물 10개:"+result.getContent());
        log.info("현재 페이지 번호:"+result.getNumber());
        log.info("크기:"+result.getSize());
    }

    @Test
    public void testQueryString(){
        Pageable pageable = PageRequest.of(0, 10, Sort.by("fno").descending());
        Page<Food> result = foodRepository.findByTitleContainingOrderByFnoDesc("3",pageable);
        log.info("총 개수:"+result.getTotalElements());
        log.info("10개씩 나눠진 데이터 총페이지:"+result.getTotalPages());
        log.info("페이징 된 결과물 10개:"+result.getContent());
        log.info("현재 페이지 번호:"+result.getNumber());
        log.info("크기:"+result.getSize());

    }

    @Test
    public void testQueryAnotation(){
        Pageable pageable = PageRequest.of(0, 10, Sort.by("fno").descending());
        Page<Food> result = foodRepository.findByKeyword("3",pageable);
        log.info("총 개수:"+result.getTotalElements());
        log.info("10개씩 나눠진 데이터 총페이지:"+result.getTotalPages());
        log.info("페이징 된 결과물 10개:"+result.getContent());
        log.info("현재 페이지 번호:"+result.getNumber());
        log.info("크기:"+result.getSize());
    }

    @Test
    public void testQuerydsl(){
        Pageable pageable = PageRequest.of(0, 10, Sort.by("fno").descending());
        foodRepository.search(pageable);
    }

    @Test
    public void testQuerydsl2(){
        Pageable pageable = PageRequest.of(0, 10, Sort.by("fno").descending());

        String keyword = "3";
        String[] types = {"t","w","c"};
        Page<Food> result = foodRepository.searchAll(types,keyword,pageable);

        log.info("총 개수:"+result.getTotalElements());
        log.info("10개씩 나눠진 데이터 총페이지:"+result.getTotalPages());
        log.info("페이징 된 결과물 10개:"+result.getContent());
        log.info("현재 페이지 번호:"+result.getNumber());
        log.info("크기:"+result.getSize());
        log.info("다음여부:"+result.hasNext());
        log.info("이전여부:"+result.hasPrevious());
    }
}
