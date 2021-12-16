package me.swim.currencycalc.controller;

import lombok.extern.slf4j.Slf4j;
import me.swim.currencycalc.dto.ReceivingAmountReqDto;
import me.swim.currencycalc.dto.ReceivingAmountResDto;
import me.swim.currencycalc.service.CurrencyCalcService;
import me.swim.currencycalc.util.CurrencyFormatUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;

@RequestMapping("/api")
@RestController
public class CurrencyCalcController {

    private final CurrencyCalcService currencyCalcService;

    public CurrencyCalcController(CurrencyCalcService currencyCalcService) {
        this.currencyCalcService = currencyCalcService;
    }

    @GetMapping("/exchange-rate")
    public ResponseEntity<String> getExchangeRate(String receivingCountry){
        return ResponseEntity.ok(CurrencyFormatUtil.formatCurrency(currencyCalcService.getExchangeRate(receivingCountry)));
    }

    @GetMapping("/receiving-amount")
    public ResponseEntity<ReceivingAmountResDto> getReceivingAmount(@Validated ReceivingAmountReqDto reqDto){
        return ResponseEntity.ok(currencyCalcService.getReceivingAmount(reqDto));
    }

}
