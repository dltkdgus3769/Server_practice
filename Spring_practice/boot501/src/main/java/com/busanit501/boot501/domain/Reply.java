package com.busanit501.boot501.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Reply", indexes = {
        @Index(name = "idx_reply_board_bno", columnList = "board_bno")
})
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Reply extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rno;// 댓글의 구분 번호,

    @ManyToOne(fetch = FetchType.LAZY) // 사용하는 시점에 조회함.
    // FetchType.EAGER , 즉시 로딩, 사용 안해도 조회 하겠다.
    private Board board; // 부모의 게시글 번호,

    private String replyText;

    private String replyer;

    public void changeBoard(Board board) {
        this.board = board;
    }

    public void changeReplyTextReplyer(String replyText, String replyer) {
        this.replyText = replyText;
        this.replyer = replyer;
    }
}
