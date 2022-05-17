package org.assignment.crypto;

import org.assignment.crypto.service.CryptocompareService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

public class CryptocompareServiceTests {

    private final CryptocompareService cryptocompareService = new CryptocompareService("https://min-api.cryptocompare.com");

    @Test
    public void getPriceBySymbolSuccessTest() {
        BigDecimal value = cryptocompareService.getPriceBySymbol("BTC", "EUR");
        Assertions.assertNotNull(value);
    }

}
