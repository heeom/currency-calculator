package me.swim.currencycalc.service;

import lombok.extern.slf4j.Slf4j;
import me.swim.currencycalc.dto.ExchangeRateInfoDto;
import me.swim.currencycalc.dto.ReceivingAmountReqDto;
import me.swim.currencycalc.dto.ReceivingAmountResDto;
import me.swim.currencycalc.util.CurrencyFormatUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;


@Slf4j
@Service
public class CurrencyCalcService {

    private final WebClient webClient;
    private final String ACCESS_KEY;
    private final String remitter;

    public CurrencyCalcService(WebClient webClient,
                               @Value("${currencylayer.access-key}") String key,
                                @Value("${currencylayer.remitter}") String remitter) {
        this.webClient = webClient;
        this.ACCESS_KEY = key;
        this.remitter = remitter;
    }

    public double getExchangeRate(String receivingCountry) {
        ExchangeRateInfoDto exchangeRateResult = getCurrencyLayerApi(receivingCountry);
        return exchangeRateResult.getQuotes()
                .getOrDefault(remitter+receivingCountry,0.0);
    }

    private ExchangeRateInfoDto getCurrencyLayerApi(String receivingCountry) {
        return webClient.get()
                .uri("/live?access_key=" + ACCESS_KEY
                        + "&source=" + remitter
                        + "&currencies=" + receivingCountry
                        + "&format=1")
                .retrieve()
                .bodyToMono(ExchangeRateInfoDto.class)
                .block();
    }

    public ReceivingAmountResDto getReceivingAmount(ReceivingAmountReqDto reqDto){
        double exchangeRate = getExchangeRate(reqDto.getReceivingCountry());

        return ReceivingAmountResDto.builder()
                .quote(CurrencyFormatUtil.formatCurrency(exchangeRate))
                .receivingAmount(CurrencyFormatUtil.formatCurrency(exchangeRate * reqDto.getAmount())).build();
    }
}
