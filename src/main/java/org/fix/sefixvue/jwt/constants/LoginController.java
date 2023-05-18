package org.fix.sefixvue.jwt.constants;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.fix.sefixvue.common.security.domain.AuthenticationRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
public class LoginController {
    private SefixProperties sefixProperties;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody AuthenticationRequest authenticationRequest) {
        String username = authenticationRequest.getUsername();
        String password = authenticationRequest.getPassword();

        log.info("username = " + username + " password = " + password);

        List<String> roles = new ArrayList<String>();
        roles.add("ROLE_MEMBER");

        // 삭제
        /*
        byte[] signingKey = SecurityConstants.JWT_SECRET.getBytes();
        */

        // 프로퍼티 파일로부터 문자열형태의 비밀키를 가져와서 바이트 배열로 변환
        byte[] signingKey = sefixProperties.getSecretKey().getBytes();

        String token = Jwts.builder()
                .signWith(Keys.hmacShaKeyFor(signingKey), SignatureAlgorithm.HS512)
                .setHeaderParam("typ", SecurityConstants.TOKEN_TYPE)
                .setExpiration(new Date(System.currentTimeMillis() + 864000000))
                .claim("uid", username)
                .claim("rol", roles)
                .compact();

        log.info("token : " + token);

        return new ResponseEntity<String>(token, HttpStatus.OK);
    }
}
