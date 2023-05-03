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
    private long event_no;    // 이벤트 순번
    private String event_title;    // 이벤트 제목
    private String event_content;    // 이벤트 작성 내용
    private String event_file;    // 이벤트 첨부 문서
    private String event_img;    // 이벤트 첨부 이미지
    private java.sql.Date event_date;    // 이벤트 작성 일시
}
