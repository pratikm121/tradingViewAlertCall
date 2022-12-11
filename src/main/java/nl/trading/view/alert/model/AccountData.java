package nl.trading.view.alert.model;

import lombok.Data;

@Data
public class AccountData {
    private double accountEquity;
    private double unrealisedPNL;
    private double marginBalance;
    private double positionMargin;
    private int orderMargin;
    private int frozenFunds;
    private double availableBalance;
    private String currency;
}

