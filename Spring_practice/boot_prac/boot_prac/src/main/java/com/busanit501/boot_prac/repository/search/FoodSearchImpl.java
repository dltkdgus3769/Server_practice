package com.busanit501.boot_prac.repository.search;

import com.busanit501.boot_prac.domain.Food;
import com.busanit501.boot_prac.domain.QFood;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.JPQLQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

public class FoodSearchImpl extends QuerydslRepositorySupport implements FoodSearch {

    public FoodSearchImpl() {
        super(Food.class);
    }

    //방법3
    @Override
    public Page<Food> search(Pageable pageable) {
        //예시,
        QFood food = QFood.food;
        JPQLQuery<Food> query = from(food);
        query.where(food.title.contains("3"));
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        booleanBuilder.or(food.title.contains("3"));
        booleanBuilder.or(food.content.contains("7"));
        query.where(booleanBuilder);
        query.where(food.fno.gt(0L));

        this.getQuerydsl().applyPagination(pageable, query);
        List<Food> list = query.fetch();
        long total = query.fetchCount();
        //
        return null;
    }

    @Override
    public Page<Food> searchAll(String[] types, String keyword, Pageable pageable) {
        QFood food = QFood.food;
        JPQLQuery<Food> query = from(food);
        if(types != null && types.length > 0 && keyword != null) {
            BooleanBuilder booleanBuilder = new BooleanBuilder();
            for(String type : types) {
                switch(type){
                    case "t":
                        booleanBuilder.or(food.title.contains(keyword));
                        break;
                    case "c":
                        booleanBuilder.or(food.content.contains(keyword));
                        break;
                    case "w":
                        booleanBuilder.or(food.writer.contains(keyword));
                        break;
                }
            }
            query.where(booleanBuilder);
        }
        query.where(food.fno.gt(0L));
        this.getQuerydsl().applyPagination(pageable, query);

        List<Food> list = query.fetch();
        long total = query.fetchCount();

        Page<Food> result = new PageImpl<Food>(list, pageable, total);
        return result;
    }


}
