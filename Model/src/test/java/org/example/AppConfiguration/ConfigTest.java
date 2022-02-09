package org.example.AppConfiguration;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ConfigTest {

    @Test
    void loadOption() {
        Config config = new Config();
        config.LoadOption();
    }

    @Test
    void setBundlePL() {
        Config config = new Config();
        config.setBundlePL();
        assertEquals(config.getLocale().getCountry(), "PL");
    }

    @Test
    void setBundleENG() {
        Config config = new Config();
        config.setBundleENG();
        assertEquals(config.getLocale().getCountry(), "ENG");
    }
}