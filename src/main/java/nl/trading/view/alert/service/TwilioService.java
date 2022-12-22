package nl.trading.view.alert.service;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Call;
import com.twilio.type.PhoneNumber;
import com.twilio.type.Twiml;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


@Service
public class TwilioService {

    @Value("${twillio.account.sid}")
    private String accountSid;

    @Value("${twillio.account.authtoken}")
    private String authToken;

    @Value("${twillio.account.to}")
    private String toNumber;

    @Value("${twillio.account.from}")
    private String fromNumber;

    // https://www.twilio.com/docs/voice/tutorials/how-to-make-outbound-phone-calls/java
    public void makeCall(String msg){
        Twilio.init(accountSid, authToken);
        PhoneNumber to = new com.twilio.type.PhoneNumber(toNumber);
        PhoneNumber from = new com.twilio.type.PhoneNumber(fromNumber);
        Twiml toSay = new com.twilio.type.Twiml("<Response><Say>"+ msg+"</Say></Response>");
        Call.creator(to , from, toSay ).create();
    }
}
