package nl.trading.view.alert.service;

import nl.trading.view.alert.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KucoinService {

    private final KucoinAPIClient client;

    @Autowired
    public KucoinService(KucoinAPIClient client){
        this.client = client;
    }

    public AccountData getAccountDetails(){
        AccountDetails accountDetails = this.client.getAccountDetails();
        return accountDetails.getData();
    }

    public ActivePositionDetails getActivePosition() { return this.client.getActivePosition().getData();}

    public TickerInformationDetails getTickerInfo() { return this.client.getTickerInfo().getData();}


}
