package org.fix.sefixvue.board.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NoticeDto {
    private long notice_no;    // 공지사항 순번
    private String notice_title;    // 공지사항 제목
    private String notice_content;    // 공지사항 작성 내용
    private String notice_file;    // 공지사항 첨부 문서
    private String notice_img;    // 공지사항 첨부 이미지
    private java.sql.Date notice_date;    // 공지사항 작성 일시

}
