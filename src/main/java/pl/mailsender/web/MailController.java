package pl.mailsender.web;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.mailsender.domain.service.MailService;

@Slf4j
@RestController
@RequestMapping("/mailsender")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@AllArgsConstructor
public class MailController {

    @Autowired
    MailService mailService;

    @GetMapping("/newlead")
    @PreAuthorize("permitAll")
    public ResponseEntity sendNewLead() {
        mailService.newLeadMessage();
        return new ResponseEntity("New information about payment with ID, " +
                "was sent to tenant", HttpStatus.OK);
    }

    @GetMapping("/nopayment")
    @PreAuthorize("permitAll")
    public ResponseEntity sendNoPayment() {
        mailService.noPaymentMessage();
        return new ResponseEntity("New remainder about payment with ID, " +
                "was sent to tenant", HttpStatus.OK);
    }

    @GetMapping("/paid")
    @PreAuthorize("permitAll")
    public ResponseEntity sendPaidInfo() {
        mailService.inVoicePaid();
        return new ResponseEntity("Tenant paid for invoice ID", HttpStatus.OK);
    }
}
