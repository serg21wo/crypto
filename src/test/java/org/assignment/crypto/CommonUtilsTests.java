package org.assignment.crypto;

import org.assignment.crypto.common.CommonUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.Map;

public class CommonUtilsTests {

    @Test
    public void getParameterValueSuccessTest() {
        String value = CommonUtils.getParameterValue(new String[]{"filePath=/tmp/testDepo.txt"}, "filePath");
        Assertions.assertEquals("/tmp/testDepo.txt", value);
    }

    @Test
    public void readPortfolioFileSuccessTest() throws URISyntaxException {

        URL res = getClass().getClassLoader().getResource("test_depo.txt");
        File file = Paths.get(res.toURI()).toFile();
        String testFilePath = file.getAbsolutePath();

        Map value = CommonUtils.readPortfolioFile(testFilePath);
        Assertions.assertTrue(value.containsKey("BTC"));
        Assertions.assertTrue(value.containsKey("XRP"));
        Assertions.assertTrue(value.containsKey("ETH"));
    }

    @Test
    public void readPropertySuccessTest() {
        String value = CommonUtils.readProperty("cryptocompareURL");
        Assertions.assertEquals("https://min-api.cryptocompare.com", value);
    }

}
