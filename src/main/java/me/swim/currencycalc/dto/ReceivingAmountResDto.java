package me.swim.currencycalc.dto;

import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class ReceivingAmountResDto {

    private String quote;
    private String receivingAmount;
}
