package org.fix.sefixvue.board.entity;


import jdk.jfr.Enabled;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "Event")
@Entity
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @SequenceGenerator(name = "mySequence", sequenceName = "event_seq", allocationSize = 1)
    @Column(name = "event_no")
    private long eventno;    // 이벤트 순번
    @Column(name = "event_title")
    private String eventtitle;    // 이벤트 제목
    @Column(name = "event_content")
    private String eventcontent;    // 이벤트 작성 내용
    @Column(name = "event_file")
    private String eventfile;    // 이벤트 첨부 문서
    @Column(name = "event_img")
    private String eventimg;    // 이벤트 첨부 이미지
    @Column(name = "event_date")
    private java.sql.Date eventdate;    // 이벤트 작성 일시
}
