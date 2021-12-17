package me.swim.currencycalc.service;

import lombok.extern.slf4j.Slf4j;
import me.swim.currencycalc.dto.ExchangeRateInfoDto;
import me.swim.currencycalc.dto.ReceivingAmountReqDto;
import me.swim.currencycalc.dto.ReceivingAmountResDto;
import me.swim.currencycalc.util.CurrencyFormatUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientRequestException;

import javax.validation.constraints.NotBlank;


@Slf4j
@Service
public class CurrencyCalcService {

    private final WebClient webClient;
    private final String access_key;
    private final String remitter;

    public CurrencyCalcService(WebClient webClient,
                               @Value("${currencylayer.access-key}") String key,
                                @Value("${currencylayer.remitter}") String remitter) {
        this.webClient = webClient;
        this.access_key = key;
        this.remitter = remitter;
    }

    public double getExchangeRate(String receivingCountry) {
        ExchangeRateInfoDto infoDto = getCurrencyLayerApi(receivingCountry);
        checkSuccess(infoDto);
        return infoDto.getQuotes().get(remitter+receivingCountry);
    }

    private ExchangeRateInfoDto getCurrencyLayerApi(String receivingCountry) {

        return webClient.get()
                .uri("/live?access_key=" + access_key
                        + "&source=" + remitter
                        + "&currencies=" + receivingCountry
                        + "&format=1")
                .retrieve()
                .onStatus(status -> status.is4xxClientError() || status.is5xxServerError(),
                            clientResponse -> clientResponse.bodyToMono(String.class)
                                    .map(body -> new RuntimeException(body))) //400번 또는 500번 error -> RuntimeException
                .bodyToMono(ExchangeRateInfoDto.class)
                .block(); // 동기식
    }

    private void checkSuccess(ExchangeRateInfoDto infoDto){
        if(!infoDto.isSuccess()){
            throw new RuntimeException(infoDto.getError().get("info"));
        }
    }

    public ReceivingAmountResDto getReceivingAmount(ReceivingAmountReqDto reqDto){
        double exchangeRate = getExchangeRate(reqDto.getReceivingCountry());

        return ReceivingAmountResDto.builder()
                .quote(CurrencyFormatUtil.formatCurrency(exchangeRate))
                .receivingAmount(CurrencyFormatUtil.formatCurrency(exchangeRate * reqDto.getAmount())).build();
    }
}
