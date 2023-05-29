package org.fix.sefixvue.board.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "Notice")
@Entity
public class Notice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "notice_no")
    private long noticeno;    // 공지사항 순번
    @Column(name = "notice_title")
    private String noticetitle;    // 공지사항 제목
    @Column(name = "notice_content")
    private String noticecontent;    // 공지사항 작성 내용
    @Column(name = "notice_file")
    private String noticefile;    // 공지사항 첨부 문서
    @Column(name = "notice_img")
    private String noticeimg;    // 공지사항 첨부 이미지
    @Column(name = "notice_date")
    private LocalDateTime noticedate;    // 공지사항 작성 일시
}
