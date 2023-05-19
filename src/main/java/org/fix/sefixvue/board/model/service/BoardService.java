package org.fix.sefixvue.board.model.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.fix.sefixvue.board.entity.*;
import org.fix.sefixvue.board.model.dto.EventDto;
import org.fix.sefixvue.board.model.dto.NoticeDto;
import org.fix.sefixvue.common.Header;
import org.fix.sefixvue.common.Pagination;
import org.fix.sefixvue.common.SearchCondition;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class BoardService {
    private final NoticeRepository noticeRepository;
    private final EventRepository eventRepository;
    private final NoticeRepositoryCustom noticeRepositoryCustom;
    private final EventRepositoryCustom eventRepositoryCustom;

    public Header<List<NoticeDto>> getNoticeList(Pageable pageable, SearchCondition searchCondition) {
        List<NoticeDto> ntos = new ArrayList<>();

        Page<Notice> noticeEntities = noticeRepositoryCustom.findAllBySearchCondition(pageable, searchCondition);
        for (Notice entity : noticeEntities) {
            NoticeDto nto = NoticeDto.builder()
                    .noticeno(entity.getNoticeno())
                    .noticetitle(entity.getNoticetitle())
                    .noticecontent(entity.getNoticecontent())
                    .noticefile(entity.getNoticefile())
                    .noticeimg(entity.getNoticeimg())
                    .noticedate(entity.getNoticedate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss")))
                    .build();
            ntos.add(nto);
        }
        Pagination pagination = new Pagination(
                (int) noticeEntities.getTotalElements()
                , pageable.getPageNumber() + 1
                , pageable.getPageSize()
                , 10
        );
        return Header.OK(ntos, pagination);
    }


    public NoticeDto getNotice(Long noticeno) {
        Notice entity = noticeRepository.findById(noticeno).orElseThrow(() -> new RuntimeException("게시글을 찾을 수 없습니다."));
        // findById(id) 하면 SELECT + WHERE 작동됨. 람다 표현식 사용됨. 의미는 ~~ 조건을 만족시켰을 떄 에러 발생시켜라
        return NoticeDto.builder()
                .noticeno(entity.getNoticeno())
                .noticetitle(entity.getNoticetitle())
                .noticecontent(entity.getNoticecontent())
                .noticefile(entity.getNoticefile())
                .noticeimg(entity.getNoticeimg())
                .noticedate(entity.getNoticedate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss")))
                .build();
        // 컬럼값 하나씩 꺼내서 매핑 -> DTO 객체 리턴
    }

    public Notice create(NoticeDto noticeDto){
        Notice entity = Notice.builder()
                .noticetitle(noticeDto.getNoticetitle())
                .noticecontent(noticeDto.getNoticecontent())
                .noticefile(noticeDto.getNoticefile())
                .noticeimg(noticeDto.getNoticeimg())
                .noticedate(LocalDateTime.now())
                .build();
        return noticeRepository.save(entity);
    }


    public Notice update(NoticeDto noticeDto){
        Notice entity = noticeRepository.findById(noticeDto.getNoticeno()).orElseThrow(() -> new RuntimeException("게시물 없음"));
        entity.setNoticetitle(noticeDto.getNoticetitle());
        entity.setNoticecontent(noticeDto.getNoticecontent());
        entity.setNoticefile(noticeDto.getNoticefile());
        entity.setNoticeimg(noticeDto.getNoticeimg());
        return noticeRepository.save(entity);
    }

    public void delete(Long noticeno){
        Notice entity = noticeRepository.findById(noticeno).orElseThrow(() -> new RuntimeException("없음"));
        noticeRepository.delete(entity);
    }


    //----------------------------------------------------
    public Header<List<EventDto>> getEventList(Pageable pageable, SearchCondition searchCondition) {
        List<EventDto> etos = new ArrayList<>();

        Page<Event> eventEntities = eventRepositoryCustom.findAllBySearchCondition(pageable, searchCondition);
        for (Event entity : eventEntities) {
            EventDto eto = EventDto.builder()
                    .eventno(entity.getEventno())
                    .eventtitle(entity.getEventtitle())
                    .eventdate(entity.getEventdate())
                    .build();
            etos.add(eto);
        }

        Pagination pagination = new Pagination(
                (int) eventEntities.getTotalElements()
                , pageable.getPageNumber() + 1
                , pageable.getPageSize()
                , 10
        );
        return Header.OK(etos, pagination);


    }


    public EventDto getEvent(Long eventno) {
        Event entity = eventRepository.findById(eventno).orElseThrow(() -> new RuntimeException("게시글을 찾을 수 없습니다."));
        // findById(id) 하면 SELECT + WHERE 작동됨. 람다 표현식 사용됨. 의미는 ~~ 조건을 만족시켰을 떄 에러 발생시켜라
        return EventDto.builder()
                .eventno(entity.getEventno())
                .eventtitle(entity.getEventtitle())
                .eventcontent(entity.getEventcontent())
                .eventfile(entity.getEventfile())
                .eventimg(entity.getEventimg())
                .eventdate(entity.getEventdate())
                .build();
        // 컬럼값 하나씩 꺼내서 매핑 -> DTO 객체 리턴
    }

}

