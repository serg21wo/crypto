package org.assignment.crypto;

import org.assignment.crypto.service.CryptocompareService;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Map;

import static org.assignment.crypto.common.CommonUtils.*;

public class App {

    public static void main(String[] args) {

        if (args != null &&
            args.length > 0 &&
            Arrays.stream(args).anyMatch( a -> a.contains("filePath"))
        ) {
            String filepath = getParameterValue(args, "filePath");
            System.out.println("Start processing... " + filepath);
            CryptocompareService cryptocompareService = new CryptocompareService(readProperty("cryptocompareURL"));

            Map<String, String> portfolio = readPortfolioFile(filepath);
            BigDecimal total = portfolio.entrySet().stream()
                            .map(kv -> new BigDecimal(kv.getValue())
                                    .multiply(cryptocompareService.getPriceBySymbol(kv.getKey(), "EUR"))
                            )
                            .reduce(BigDecimal.ZERO, BigDecimal::add);

            System.out.println("The total value of portfolio: " + total + " EUR");
        } else {
            throw new RuntimeException("Missing required parameter: filePath");
        }
    }



}
