package nl.trading.view.alert.controller;

import nl.trading.view.alert.model.AccountData;
import nl.trading.view.alert.model.ActivePositionDetails;
import nl.trading.view.alert.model.TickerInformation;
import nl.trading.view.alert.model.TickerInformationDetails;
import nl.trading.view.alert.service.KucoinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/kucoin")
public class KucoinController {

    private final KucoinService kucoinService;

    @Autowired
    public KucoinController(KucoinService kucoinService){
        this.kucoinService = kucoinService;
    }

    @PostMapping("/account-details") //http://localhost:8080/kucoin/account-details
    //https://b5e3-2a02-a212-92c6-b900-896d-df91-c259-e068.eu.ngrok.io/kucoin/account-details
    public AccountData fetchDetails(@RequestBody String msg){
        return this.kucoinService.getAccountDetails();
    }

    @PostMapping("/active-position") //http://localhost:8080/kucoin/active-position
    //https://b5e3-2a02-a212-92c6-b900-896d-df91-c259-e068.eu.ngrok.io/kucoin/active-position
    public ActivePositionDetails fetchActivePosition(@RequestBody String msg){
        return this.kucoinService.getActivePosition();
    }

    @PostMapping("/ticker-info") //http://localhost:8080/kucoin/ticker-info
    //https://b5e3-2a02-a212-92c6-b900-896d-df91-c259-e068.eu.ngrok.io/kucoin/ticker-info
    //https://docs.kucoin.com/futures/#get-open-contract-list
    public TickerInformationDetails fetchTickerInfo(@RequestBody String msg){
        return this.kucoinService.getTickerInfo();
    }
}
