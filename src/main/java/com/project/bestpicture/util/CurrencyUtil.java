package com.project.bestpicture.util;

public class CurrencyUtil {

    private static final String NA = "N/A";

    public static Long currencyToLong(String currency){
        if(currency==null) return null;
        if(currency.equals(NA)) return null;
        var tmp = currency.replaceAll("\\D+", "");
        return Long.valueOf(tmp);
    }

}
