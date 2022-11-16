package pl.mailsender.web;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pl.mailsender.domain.service.MailService;

@Slf4j
@RestController
@RequestMapping("/mailsender")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@AllArgsConstructor
public class MailController {

    @Autowired
    MailService mailService;


    @PostMapping("/newlead")
    @PreAuthorize("permitAll")
    public ResponseEntity sendNewLead(@RequestBody MailLeadPayLoad mailLeadPayLoad) {
        mailService.newLeadMessage(mailLeadPayLoad);
        return new ResponseEntity("New information about payment with ID, " +
                "was sent to tenant", HttpStatus.OK);
    }

    @PostMapping("/nopayment")
    @PreAuthorize("permitAll")
    public ResponseEntity sendNoPayment(@RequestBody MailLeadPayLoad mailLeadPayLoad) {
        mailService.noPaymentMessage(mailLeadPayLoad);
        return new ResponseEntity("New remainder about payment with ID, " +
                "was sent to tenant", HttpStatus.OK);
    }

    @PostMapping("/paid")
    @PreAuthorize("permitAll")
    public ResponseEntity sendPaidInfo(@RequestBody MailLeadPayLoad mailLeadPayLoad) {
        mailService.inVoicePaid(mailLeadPayLoad);
        return new ResponseEntity("Tenant paid for invoice ID", HttpStatus.OK);
    }
}
