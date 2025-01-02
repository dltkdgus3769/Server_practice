package com.busanit501.boot501.service;

import com.busanit501.boot501.domain.Board;
import com.busanit501.boot501.dto.*;

import java.util.List;
import java.util.stream.Collectors;

public interface BoardService {
    Long register(BoardDTO boardDTO);
    BoardDTO readOne(Long bno);
    void update(BoardDTO boardDTO);
    void delete(Long bno);
    PageResponseDTO<BoardDTO> list(PageRequestDTO pageRequestDTO);

    PageResponseDTO<BoardListReplyCountDTO> listWithReplyCount(PageRequestDTO pageRequestDTO);

    //게시글+댓글+첨부
    PageResponseDTO<BoardListAllDTO> listWithAll(PageRequestDTO pageRequestDTO);

    //화면(DTO) -> 디비(엔티티)
    default Board dtoToEntity(BoardDTO boardDTO) {
        Board board = Board.builder()
                .bno(boardDTO.getBno())
                .title(boardDTO.getTitle())
                .content(boardDTO.getContent())
                .writer(boardDTO.getWriter())
                .build();
        if(boardDTO.getFileNames() != null) {
            boardDTO.getFileNames().forEach(fileName->{
                String[] array = fileName.split("_");
                board.addImage(array[0], array[1]);
            });
        }
        return board;
    }

    //디비(엔티티) -> 화면(DTO)
    default BoardDTO entityToDto(Board board) {
        BoardDTO boardDTO = BoardDTO.builder()
                .bno(board.getBno())
                .title(board.getTitle())
                .content(board.getContent())
                .writer(board.getWriter())
                .regDate(board.getRegDate())
                .modDate(board.getModDate())
                .build();
        List<String> fileNames = board.getImageSet().stream().sorted().map(boardImage ->
                boardImage.getUuid()+"_"+boardImage.getFileName()).collect(Collectors.toList());

        boardDTO.setFileNames(fileNames);
        return boardDTO;
    }
}
