package org.fix.sefixvue.common.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
@Slf4j
public class CustomNoOpPasswordEncoder implements PasswordEncoder {
    public String encode(CharSequence rawPassword) {
        log.info("before encode :" + rawPassword);

        return rawPassword.toString();
    }

    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        log.info("matches: " + rawPassword + ":" + encodedPassword);

        return rawPassword.toString().equals(encodedPassword);
    }
}
