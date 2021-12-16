package me.swim.currencycalc.dto;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;


@Getter @Setter
public class ReceivingAmountReqDto {

    @NotBlank
    private String receivingCountry;

    @Range(min = 0, max = 10000)
    private double amount;

}
