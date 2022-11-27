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
    @Autowired
    MailLeadPayLoad mailLeadPayLoad;

    public void newLeadMessage(MailLeadPayLoad mailLeadPayLoad) {
        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setTo("mxlazarewicz@gmail.com");

            helper.setSubject("Wystawiliśmy  Ci nową fakturę");

            helper.setText("Witaj, " + mailLeadPayLoad.getTenant() + "\n" +
                    " Wystawiliśmy Ci nową fakutrę o numerze " + mailLeadPayLoad.getLeadId() + "\n" +
                    " za czynsz administracyjy w kwocie" + mailLeadPayLoad.getAdministrativeRent() + "\n" +
                    " energię elektryczną " + mailLeadPayLoad.getElectricityPayment() + "\n" +
                    " i zużycie wody" + mailLeadPayLoad.getWaterPayment() + "\n" +
                    " Prosimy o płatność w terminie.");

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

            helper.setText("Faktura z dnia " + mailLeadPayLoad.getCreationDate() +
                    "nie została opłacona w terminie" + "\n" +
                    "Prosimy o natychmiastowe uregulowanie płatności.");

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

            helper.setText("Faktura o numerze " + mailLeadPayLoad.getLeadId() + "\n" +
                    "została opłacona przez użytkownika " + mailLeadPayLoad.getTenant());

            javaMailSender.send(message);

        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }
}


