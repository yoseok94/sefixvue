package org.fix.sefixvue.board.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "Notice")
@Entity
public class Notice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @SequenceGenerator(name = "mySequence", sequenceName = "notice_seq", allocationSize = 1)
    @Column(name = "notice_no")
    private long notice_no;    // 공지사항 순번
    @Column(name = "notice_title")
    private String notice_title;    // 공지사항 제목
    @Column(name = "notice_content")
    private String notice_content;    // 공지사항 작성 내용
    @Column(name = "notice_file")
    private String notice_file;    // 공지사항 첨부 문서
    @Column(name = "notice_img")
    private String notice_img;    // 공지사항 첨부 이미지
    @Column(name = "notice_date")
    private java.sql.Date notice_date;    // 공지사항 작성 일시
}
