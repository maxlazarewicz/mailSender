package pl.mailsender.web;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MailLeadPayLoad {

    private long leadId;
    private String owner;
    private String tenant;
    private LocalDateTime creationDate;
    private String electricityPayment;
    private String waterPayment;
    private String administrativeRent;

}
