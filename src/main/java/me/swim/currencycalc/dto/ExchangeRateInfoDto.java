package me.swim.currencycalc.dto;

import lombok.*;

import java.util.HashMap;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class ExchangeRateInfoDto {
    private boolean success;
    private String terms;
    private String privacy;
    private String timestamp;
    private String source;
    private HashMap<String, Double> quotes;
    private HashMap<String, String> error;
}
