//package org.fix.sefixvue.accounting.controller;
//
//import lombok.extern.slf4j.Slf4j;
//import org.fix.sefixvue.accounting.model.dto.MailDto;
//import org.fix.sefixvue.accounting.model.service.MailService;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.GetMapping;
//import java.io.IOException;
//import java.util.List;
//import javax.mail.MessagingException;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import lombok.extern.slf4j.Slf4j;
//
//    @Slf4j
//    @Controller
//    public class MailController {
//
//        private final MailService mailService;
//
//
//        public MailController(MailService mailService) {
//            this.mailService = mailService;
//        }
//
//        @GetMapping("/templateMail")
//        public String templateMailSend() {
//            return "templateMail";
//        }
//
//        @PostMapping("/mail/template/send")
//        public String sendTemplateMail(MailDto mailDto, Model model) throws MessagingException {
//            mailService.sendTemplateMessage(mailDto);
//            System.out.println("메일 전송 완료");
//            return "result";
//        }
//    }
