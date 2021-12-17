package me.swim.currencycalc.service;

import me.swim.currencycalc.dto.ReceivingAmountReqDto;
import me.swim.currencycalc.dto.ReceivingAmountResDto;
import me.swim.currencycalc.util.CurrencyFormatUtil;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
class CurrencyCalcServiceTest {

    @Autowired
    CurrencyCalcService currencyCalcService;

    @DisplayName("수취국 환율 조회")
    @Test
    void getExchangeRate() {
        String receivingCountry = "KRW";

        double exchangeRate = currencyCalcService.getExchangeRate(receivingCountry);

        System.out.println(exchangeRate);
        assertThat(CurrencyFormatUtil.formatCurrency(exchangeRate)).isEqualTo("1,185.47");
    }

    @DisplayName("수취 금액계산")
    @Test
    void getReceivingAmount(){
        ReceivingAmountReqDto reqDto = new ReceivingAmountReqDto();
        reqDto.setAmount(100);
        reqDto.setReceivingCountry("KRW");

        ReceivingAmountResDto receivingAmount = currencyCalcService.getReceivingAmount(reqDto);

        System.out.println(receivingAmount.getReceivingAmount());
    }

    @DisplayName("송금액 입력값 유효성검사: 0이상 10000이하 숫자 값")
    @Test
    void beanValidation(){
        ReceivingAmountReqDto reqDto = new ReceivingAmountReqDto();
        reqDto.setAmount(-1.0); //0 이하
        reqDto.setAmount(99999.0); //10000 이상
        reqDto.setReceivingCountry(" "); //공백

        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        Validator validator = validatorFactory.getValidator();

        Set<ConstraintViolation<ReceivingAmountReqDto>> validate = validator.validate(reqDto);
        for (ConstraintViolation<ReceivingAmountReqDto> v :validate){
            System.out.println("유효성 검사: "+v.getMessage());
        }
    }

    @DisplayName("수취국 환율 조회 NullPoint Exception 핸들링")
    @Test
    void getExchangeRate_Exception(){
        String receivingCountry = "ERROR";

        try {
            currencyCalcService.getExchangeRate(receivingCountry);
        }catch(NullPointerException e){
            System.out.println(e.getMessage());
        }

    }


}