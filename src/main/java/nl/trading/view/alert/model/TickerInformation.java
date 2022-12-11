package nl.trading.view.alert.model;

import lombok.Data;

@Data
public class TickerInformation {
    private String code;
    private TickerInformationDetails data;

}
