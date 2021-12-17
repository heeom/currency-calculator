package me.swim.currencycalc.util;

import java.text.DecimalFormat;

public class CurrencyFormatUtil {

    private static final DecimalFormat decimalFormat = new DecimalFormat("###,##0.00");

    public static String formatCurrency(double number){
        return decimalFormat.format(number);
    }
}
