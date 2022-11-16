package pl.mailsender.domain.service;


import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import pl.mailsender.web.MailLeadPayLoad;

import javax.mail.internet.MimeMessage;
import javax.transaction.Transactional;

@Slf4j
@Service
@AllArgsConstructor
public class MailService {

    @Autowired
    JavaMailSender javaMailSender;

    public void newLeadMessage(MailLeadPayLoad mailLeadPayLoad) {
        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setTo("mxlazarewicz@gmail.com");

            helper.setSubject("Wystawiliśmy Ci nową fakturę");

            helper.setText("Wystawiliśmy Ci nową fakturę za użytkowanie lokalu przy ul. " +
                    "+ propertiesAdress  " + "w kwocie +  {kwota} " + "\n" +
                    "Prosimy o wpłate w terminie");

            javaMailSender.send(message);

        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

    public void noPaymentMessage(MailLeadPayLoad mailLeadPayLoad) {

        try {

            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setTo("mxlazarewicz@gmail.com");

            helper.setSubject("Faktura nie została zapłacona w terminie");

            helper.setText("Faktura z dnia + {lead.creationdate} nie została opłacona" + "\n"
                    + "Prosimy o natychmiastowe uregulowanie płatności" + "\n" +
                    "Ps. Chłopaki już po Ciebie jadą.");

            javaMailSender.send(message);

        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

    public void inVoicePaid(MailLeadPayLoad mailLeadPayLoad) {

        try {

            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setTo("mxlazarewicz@gmail.com");

            helper.setSubject("Nowa płatność od wynajmującego");

            helper.setText("Faktura z dnia {date} została opłacona");

            javaMailSender.send(message);

        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }
}


