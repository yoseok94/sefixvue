package org.fix.sefixvue.board.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.fix.sefixvue.board.entity.Event;
import org.fix.sefixvue.board.entity.Notice;
import org.fix.sefixvue.board.model.dto.EventDto;
import org.fix.sefixvue.board.model.dto.NoticeDto;
import org.fix.sefixvue.board.model.service.BoardService;
import org.fix.sefixvue.common.Header;
import org.fix.sefixvue.common.SearchCondition;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@CrossOrigin
@RestController
public class BoardController {
    private final BoardService boardService;

    @GetMapping("/notice/list")
    public Header<List<NoticeDto>> noticeList(
            @PageableDefault(sort = {"noticeno"}) Pageable pageable,
            SearchCondition searchCondition
    ) {
        return boardService.getNoticeList(pageable, searchCondition);
    }

    @GetMapping("/notice/{noticeno}")
    public NoticeDto getNotice(@PathVariable Long noticeno) {
        return boardService.getNotice(noticeno);
    }

    @PostMapping("/notice")
    public Notice noticeWrite(@RequestBody NoticeDto noticeDto) {

        return boardService.create(noticeDto);
    }

    @PatchMapping("/notice")
    public Notice noticeUp(@RequestBody NoticeDto noticeDto) {
        return boardService.update(noticeDto);
    }
//
    @DeleteMapping("/notice/{noticeno}")
    public void noticeDel(@PathVariable Long noticeno) {
        boardService.deleteNoti(noticeno);
    }
    //--------------------------------



        @GetMapping("/event/list")
        public Header<List<EventDto>> eventList(
                @PageableDefault(sort = {"eventno"}) Pageable pageable,
                SearchCondition searchCondition
        ) {
            return boardService.getEventList(pageable, searchCondition);
        }

        @GetMapping("/event/{eventno}")
        public EventDto getEvent(@PathVariable Long eventno) {
            return boardService.getEvent(eventno);
        }

        @PostMapping("/event")
        public Event eventWrite(@RequestBody EventDto eventDto) {

            return boardService.create(eventDto);
        }
        @PatchMapping("/event")
        public Event eventUp(@RequestBody EventDto eventDto) {
            return boardService.update(eventDto);
        }
        //
        @DeleteMapping("/event/{eventno}")
        public void eventDel(@PathVariable Long eventno) {
            boardService.deleteEve(eventno);
        }
}
