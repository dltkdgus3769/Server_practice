package com.busanit501.helloworld.jdbcex.util;

import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration;
import org.modelmapper.convention.MatchingStrategies;
import org.modelmapper.spi.MatchingStrategy;

public enum MapperUtil {
    // final static 사용됨.
    INSTANCE;

    private ModelMapper modelMapper;

    MapperUtil() {
        this.modelMapper = new ModelMapper();
        this.modelMapper.getConfiguration()
                // VO <-> DTO , 멤버의 일치서 여부를 확인하는 설정.
                .setFieldMatchingEnabled(true)
                // 멤버 접근시 private 접근 지정자에 접근 가능하게하는 설정.
                .setFieldAccessLevel(Configuration.AccessLevel.PRIVATE)
                // 타입이 정확하게 일치하는지 여부를 꼼꼼하게, 엄격하게 검사하겠다 설정.
                .setMatchingStrategy(MatchingStrategies.STRICT);
    } //
    public ModelMapper get() {
        return modelMapper;
    }
}
