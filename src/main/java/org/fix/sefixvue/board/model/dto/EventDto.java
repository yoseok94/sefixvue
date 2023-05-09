package org.fix.sefixvue.board.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EventDto {
    private long eventno;    // 이벤트 순번
    private String eventtitle;    // 이벤트 제목
    private String eventcontent;    // 이벤트 작성 내용
    private String eventfile;    // 이벤트 첨부 문서
    private String eventimg;    // 이벤트 첨부 이미지
    private java.sql.Date eventdate;    // 이벤트 작성 일시
}
