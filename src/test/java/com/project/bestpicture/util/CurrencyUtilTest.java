package com.project.bestpicture.util;

import org.junit.jupiter.api.Test;

import static com.project.bestpicture.util.CurrencyUtil.currencyToLong;
import static org.junit.jupiter.api.Assertions.*;

class CurrencyUtilTest {

    @Test
    void NAshouldReturnNull(){
        assertNull(currencyToLong("N/A"));
    }

    @Test
    void correctCurrencyShouldGiveCorrectValue(){
        assertEquals(85080171,currencyToLong("$85,080,171"));
        assertEquals(292587330,currencyToLong("$292,587,330"));
    }

}