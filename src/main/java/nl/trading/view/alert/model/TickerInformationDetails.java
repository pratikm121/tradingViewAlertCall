package nl.trading.view.alert.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class TickerInformationDetails {

    private String symbol;
    private String rootSymbol;
    private String type;
    private long firstOpenDate;
    private String expireDate;
    private String settleDate;
    private String baseCurrency;
    private String quoteCurrency;
    private String settleCurrency;
    private int maxOrderQty;
    private double maxPrice;
    private int lotSize;
    private double tickSize;
    private double indexPriceTickSize;
    private double multiplier;
    private double initialMargin;
    private double maintainMargin;
    private int maxRiskLimit;
    private int minRiskLimit;
    private int riskStep;
    private double makerFeeRate;
    private double takerFeeRate;
    private double takerFixFee;
    private double makerFixFee;
    private double settlementFee;
    @JsonProperty(value = "isDeleverage")
    private boolean isDeleverage;
    @JsonProperty(value = "isQuanto")
    private boolean isQuanto;
    @JsonProperty(value = "isInverse")
    private boolean isInverse;
    private String markMethod;
    private String fairMethod;
    private String fundingBaseSymbol;
    private String fundingQuoteSymbol;
    private String fundingRateSymbol;
    private String indexSymbol;
    private String settlementSymbol;
    private String status;
    private double fundingFeeRate;
    private double predictedFundingFeeRate;
    private String openInterest;
    private double turnoverOf24h;
    private double volumeOf24h;
    private double markPrice;
    private double indexPrice;
    private double lastTradePrice;          // Current price
    private int nextFundingRateTime;
    private int maxLeverage;
    private List<String> sourceExchanges;
    private String premiumsSymbol1M;
    private String premiumsSymbol8H;
    private String fundingBaseSymbol1M;
    private String fundingQuoteSymbol1M;
    private double lowPrice;            // 24hr low
    private double highPrice;           // 24hr high
    private double priceChgPct;
    private double priceChg;
}
