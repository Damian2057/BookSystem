package org.example.dao.filemodel;

import org.example.AppConfiguration.Config;
import org.example.dao.ClassFactory;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

class FilesaverSystemTest {

    @Test
    void read() {
        try(var system = ClassFactory.getFileSaverSystem("test")) {
            Config config = new Config();
            config.setBundlePL();
            system.write(config);
            Config config1 = system.read();
            assertEquals(config.getLocale().getCountry(),config1.getLocale().getCountry());
            File myObj = new File("test");
            myObj.delete();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}