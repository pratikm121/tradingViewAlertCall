package nl.trading.view.alert.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import nl.trading.view.alert.model.TradingViewRequest;
import nl.trading.view.alert.service.TwilioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/trading-view")
public class TradingViewController {

    private final TwilioService twilioService;

    @Autowired
    public TradingViewController(TwilioService twilioService){
        this.twilioService = twilioService;
    }

    @PostMapping("/alert") //http://localhost:7979/trading-view/alert
    public void makeAlertCall(@RequestBody String request) throws JsonProcessingException {
        log.info("Request {}",request);
        ObjectMapper mapper = new ObjectMapper();
        TradingViewRequest tradingViewRequest = mapper.readValue(request, TradingViewRequest.class);
        log.info("TradingViewRequest {}",tradingViewRequest);
        this.twilioService.makeCall(request);
    }
}
