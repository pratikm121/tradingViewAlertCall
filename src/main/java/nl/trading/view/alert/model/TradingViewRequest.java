package nl.trading.view.alert.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class TradingViewRequest implements Serializable {
    private String user;
    private double price;
    private String ticker;
    private String timestamp;
    private String interval;
    private String open;
    private String close;
    private String high;
    private String low;
}
