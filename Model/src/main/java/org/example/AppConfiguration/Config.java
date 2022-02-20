package org.example.AppConfiguration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.Locale;
import java.util.ResourceBundle;

public class Config implements Serializable {
    private Locale locale;
    private String AppGeneralPassword;
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public void LoadOption() {
        try {
            if (locale.getCountry().equals("PL")) {
                Locale.setDefault(new Locale("pl", "PL"));
            }
            else {
                Locale.setDefault(new Locale("eng", "ENG"));
            }
        } catch (Exception e) {
            logger.error("Error connected with language");
        }
    }

    public Locale getLocale() {
        return locale;
    }

    public void setBundlePL() {
        Locale.setDefault(new Locale("pl", "PL"));
        locale = Locale.getDefault();
    }

    public void setBundleENG() {
        Locale.setDefault(new Locale("eng", "ENG"));
        locale = Locale.getDefault();
    }

    public String getAppGeneralPassword() {
        return AppGeneralPassword;
    }

    public void setAppGeneralPassword(String appGeneralPassword) {
        AppGeneralPassword = appGeneralPassword;
    }
}
