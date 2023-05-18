//package org.fix.sefixvue.accounting.model.service;
//
//import java.io.IOException;
//import java.util.HashMap;
//import java.util.List;
//
//import javax.mail.MessagingException;
//import javax.mail.internet.MimeMessage;
//import javax.mail.internet.MimeUtility;
//
//import org.fix.sefixvue.accounting.model.dto.MailDto;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.core.io.ByteArrayResource;
//import org.springframework.core.io.ClassPathResource;
//import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.mail.javamail.MimeMessageHelper;
//import org.springframework.stereotype.Service;
//import org.springframework.util.StringUtils;
//import org.thymeleaf.context.Context;
//import org.thymeleaf.spring5.SpringTemplateEngine;
//
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//
//@Slf4j
//@Service
//@RequiredArgsConstructor
//public class MailService {
//    private final JavaMailSender emailSender;
//    private final SpringTemplateEngine templateEngine;
//
//    public void sendTemplateMessage(MailDto mailDto) throws MessagingException {
//        MimeMessage message = emailSender.createMimeMessage();
//        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
//        //메일 제목 설정
//        helper.setSubject(mailDto.getTitle());
//
//        //수신자 설정
//        helper.setTo(mailDto.getAddress());
//
//        //참조자 설정
//        helper.setCc(mailDto.getCcAddress());
//
//        //템플릿에 전달할 데이터 설정
//        HashMap<String, String> emailValues = new HashMap<>();
//        emailValues.put("name", "jimin");
//
//        Context context = new Context();
//        emailValues.forEach((key, value) -> {
//            context.setVariable(key, value);
//        });
//
//        //메일 내용 설정 : 템플릿 프로세스
//        String html = templateEngine.process(mailDto.getTemplate(), context);
//        helper.setText(html, true);
//
//        //템플릿에 들어가는 이미지 cid로 삽입
//        helper.addInline("image1", new ClassPathResource("static/images/image-1.jpeg"));
//
//        //메일 보내기
//        emailSender.send(message);
//    }
//}