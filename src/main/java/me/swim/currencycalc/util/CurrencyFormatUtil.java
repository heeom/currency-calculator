package me.swim.currencycalc.util;


import java.text.DecimalFormat;

public class CurrencyFormatUtil {
    private static final String FORMAT = "###,##0.00";

    public static String formatCurrency(double number){
        DecimalFormat decimalFormat = new DecimalFormat(FORMAT);
        return decimalFormat.format(number);
    }
}
