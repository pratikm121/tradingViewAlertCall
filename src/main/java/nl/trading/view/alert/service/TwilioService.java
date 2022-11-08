package nl.trading.view.alert.service;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Call;
import com.twilio.type.PhoneNumber;
import com.twilio.type.Twiml;
import org.springframework.stereotype.Service;


@Service
public class TwilioService {

    public static final String ACCOUNT_SID = "AC6659ab7b1d732e64402cbccaf2860883";
    public static final String AUTH_TOKEN = "4a81a7c01060d2a7c527c7e315c2a58f";

    // https://www.twilio.com/docs/voice/tutorials/how-to-make-outbound-phone-calls/java
    public void makeCall(String msg){
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        PhoneNumber to = new com.twilio.type.PhoneNumber("+31619795826");
        PhoneNumber from = new com.twilio.type.PhoneNumber("+31623629628");
        Twiml toSay = new com.twilio.type.Twiml("<Response><Say>"+ msg+"</Say></Response>");
        Call.creator(to , from, toSay ).create();
    }
}
