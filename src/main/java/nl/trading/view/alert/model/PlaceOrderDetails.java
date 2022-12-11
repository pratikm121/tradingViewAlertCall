package nl.trading.view.alert.model;

import lombok.Data;

@Data
public class PlaceOrderDetails {
    private String clientOid;       //Unique order id created by users to identify their orders, e.g. UUID, Only allows numbers, characters, underline(_), and separator(-)
    private boolean reduceOnly;
    private boolean closeOrder;
    private boolean forceHold;
    private boolean hidden;
    private boolean iceberg;
    private int leverage;
    private boolean postOnly;
    private int price;              //Limit price.Required for limit order.
    private String remark;
    private String side;            //buy or sell
    private int size;               //Required for limit order size. Must be a positive number
    private String stop;            //down or up. Requires stopPrice and stopPriceType to be defined
    private int stopPrice;
    private String stopPriceType;   //TP, IP or MP, Need to be defined if stop is specified.
    private String symbol;
    private String timeInForce;
    private String type;        // limit or market
    private int visibleSize;
}
