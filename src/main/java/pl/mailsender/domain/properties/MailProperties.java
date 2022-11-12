package pl.mailsender.domain.properties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class MailProperties {

    @Value("${spring.mail.default-encoding}")
    private String defaultEncoding;
    @Value("${spring.mail.host}")
    private String host;
    @Value("${spring.mail.username}")
    private String username;
    @Value("${spring.mail.password}")
    private String password;
    @Value("${spring.mail.port}")
    private int port;
    @Value("${spring.mail.transport.protocol}")
    private String transportProtocol;
    @Value("${spring.mail.smtp.auth}")
    private String smtpAuth;
    @Value("${spring.mail.smtp.starttls.enable}")
    private String smtpStarttlsEnable;
    @Value("${spring.mail.debug}")
    private String debug;
}
