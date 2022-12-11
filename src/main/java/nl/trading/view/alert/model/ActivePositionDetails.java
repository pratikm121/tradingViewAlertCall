package nl.trading.view.alert.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ActivePositionDetails {
    private String id;
    private String symbol;
    private boolean autoDeposit;
    private double maintMarginReq;
    private int riskLimit;
    private double realLeverage;
    private boolean crossMode;
    private double delevPercentage;
    private long openingTimestamp;
    private long currentTimestamp;
    private int currentQty;
    private double currentCost;
    private double currentComm;
    private double unrealisedCost;
    private int realisedGrossCost;
    private double realisedCost;
    @JsonProperty(value = "isOpen")
    private boolean isOpen;
    private double markPrice;
    private double markValue;
    private double posCost;
    private double posCross;
    private double posInit;
    private double posComm;
    private double posLoss;
    private double posMargin;
    private double posMaint;
    private double maintMargin;
    private int realisedGrossPnl;
    private double realisedPnl;
    private double unrealisedPnl;
    private double unrealisedPnlPcnt;
    private double unrealisedRoePcnt;
    private int avgEntryPrice;
    private double liquidationPrice;
    private double bankruptPrice;
    private String settleCurrency;
    private double maintainMargin;
    private int riskLimitLevel;
}
