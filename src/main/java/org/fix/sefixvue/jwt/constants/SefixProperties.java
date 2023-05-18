package org.fix.sefixvue.jwt.constants;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties("org.fix.sefixvue")
public class SefixProperties {
    private String secretKey;
}
