package me.swim.currencycalc.dto;

import lombok.*;

import java.util.Map;

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
    private Map<String, Double> quotes;
    private Map<String, String> error;
}
