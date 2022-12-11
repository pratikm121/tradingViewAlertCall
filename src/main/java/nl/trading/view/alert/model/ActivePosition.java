package nl.trading.view.alert.model;

import lombok.Data;

@Data
public class ActivePosition {
    private String code;
    private ActivePositionDetails data;
}
