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
    private long noticeno;    // 공지사항 순번
    private String noticetitle;    // 공지사항 제목
    private String noticecontent;    // 공지사항 작성 내용
    private String noticefile;    // 공지사항 첨부 문서
    private String noticeimg;    // 공지사항 첨부 이미지
    private java.sql.Date noticedate;    // 공지사항 작성 일시

}
