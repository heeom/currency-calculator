package me.swim.currencycalc.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class ReceivingAmountReqDto {

    @NotBlank
    private String receivingCountry;

    @Range(min = 0, max = 10000)
    private double amount;

}
