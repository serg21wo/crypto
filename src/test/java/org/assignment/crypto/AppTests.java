package org.assignment.crypto;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

public class AppTests {

    @Test
    public void runAppWithoutParamsFailTest(){
        RuntimeException thrown = assertThrows(
                RuntimeException.class,
                () -> App.main(null)
        );
        assertTrue(thrown.getMessage().contains("Missing required parameter"));
    }

    @Test
    public void runAppWithParamsSuccessTest() throws URISyntaxException {
        URL res = getClass().getClassLoader().getResource("test_depo.txt");
        File file = Paths.get(res.toURI()).toFile();
        String testFilePath = file.getAbsolutePath();
        assertDoesNotThrow(() -> App.main(new String[]{"filePath=" + testFilePath}));
    }

}
