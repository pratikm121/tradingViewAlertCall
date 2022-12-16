package nl.trading.view.alert.controller;

import nl.trading.view.alert.model.TradingViewRequest;
import nl.trading.view.alert.service.TwilioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/trading-alert")
public class TwillioControlller {

    private final TwilioService twilioService;

    @Autowired
    public TwillioControlller(TwilioService twilioService){
        this.twilioService = twilioService;
    }

    @PostMapping("/call") //http://localhost:8080/trading-alert/call
    //https://f2a6-2a02-a212-92c6-b900-4461-ff77-1682-cc5d.eu.ngrok.io/trading-alert/call
    public void makeAlertCall(@RequestBody TradingViewRequest request){
        System.out.println("msg ="+request);
        String message = request.getTicker().concat(" has reached ").concat(Double.toString(request.getPrice()));
        this.twilioService.makeCall(message);
    }
}
