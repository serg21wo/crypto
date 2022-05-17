package org.assignment.crypto.service;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.HttpURLConnection;

import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class CryptocompareService {

    private final String url;

    public CryptocompareService(String url) {
        this.url = url;
    }

    private BigDecimal parsePriceBySymbolJson(String json, String currency) {
        Pattern pattern = Pattern.compile("\""+ currency +"\":(.*?)(?=,|}|$)");
        Matcher matcher = pattern.matcher(json);
        if (matcher.find()) {
            return new BigDecimal(matcher.group(1));
        }
        throw new RuntimeException("Unable to parse response from Cryptocompare service");
    }

    public BigDecimal getPriceBySymbol(String symbol, String currency) {
        String apiUrlWithParams = this.url + String.format("/data/price?fsym=%s&tsyms=%s", symbol, currency);
        String jsonResponse = httpRequestGet(apiUrlWithParams);
        return parsePriceBySymbolJson(jsonResponse, currency);
    }

    private String httpRequestGet(String requestUrl) {
        try {
            URL url = new URL(requestUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty("accept", "application/json");
            InputStream responseStream = connection.getInputStream();

            return new BufferedReader(
                    new InputStreamReader(responseStream, StandardCharsets.UTF_8))
                    .lines().collect(Collectors.joining("\n"));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}
