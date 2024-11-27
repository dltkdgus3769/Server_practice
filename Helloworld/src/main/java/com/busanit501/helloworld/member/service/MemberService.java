package com.busanit501.helloworld.member.service;


import com.busanit501.helloworld.jdbcex.util.MapperUtil;
import com.busanit501.helloworld.member.dao.MemberDAO;
import com.busanit501.helloworld.member.dto.MemberDTO;
import com.busanit501.helloworld.member.vo.MemberVO;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

@Log4j2
public enum MemberService {
    INSTANCE;

    private MemberDAO memberDAO;
    private ModelMapper modelMapper;

    // 생성자 이용해서, 초기화하기.
    MemberService(){
        memberDAO = new MemberDAO();
        modelMapper = MapperUtil.INSTANCE.get();
    }


    //1
    // register
    // 화면에서 등록된 내용이 -> DTO 박스에 담아서-> 서비스 계층에 전달.
    public void register (MemberDTO memberDTO) throws SQLException {
        // 모델 맵퍼 이용시.
        MemberVO memberVO = modelMapper.map(memberDTO,MemberVO.class);
        log.info("memberVO :"+memberVO);
        // DAO 외주 맡기기,
        memberDAO.insert(memberVO);
    }

    //2
    // 전체 조회
    public List<MemberDTO> listAll() throws SQLException {
        List<MemberVO> voList = memberDAO.selectAll();
        log.info("voList : " + voList);
        List<MemberDTO> dtoList = voList.stream().map(vo -> modelMapper.map(vo, MemberDTO.class))
                .collect(Collectors.toList());
        return dtoList;
    }

    //3
    // 상세보기
    public MemberDTO get(Long mno) throws SQLException {
        log.info("mno : " + mno);
        MemberVO memverVO = memberDAO.selectOne(mno);
        MemberDTO memberDTO = modelMapper.map(memverVO,MemberDTO.class);
        return memberDTO;
    }

    //4
    // 수정하기
    public void update(MemberDTO memberDTO) throws SQLException {
        log.info("memberDTO:"+memberDTO);
        MemberVO memberVO = modelMapper.map(memberDTO,MemberVO.class);
        memberDAO.updateOne(memberVO);

    }

    //5 삭제 기능.
    public void delete(Long mno) throws SQLException {
        memberDAO.deleteMember(mno);
    }
}
