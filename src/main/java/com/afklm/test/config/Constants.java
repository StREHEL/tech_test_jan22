package com.afklm.test.config;

/**
 * Application constants.
 */
public final class Constants {

    // Regex for acceptable logins
    public static final String LOGIN_REGEX = "^(?>[a-zA-Z0-9!$&*+=?^_`{|}~.-]+@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*)|(?>[_.@A-Za-z0-9-]+)$";

    public static final String SYSTEM = "system";
    public static final String DEFAULT_LANGUAGE = "en";
    
    /**
     * Simple regexp for ISO 3166-1 countries codes (2 ou 3 letters).
     * {@link "https://en.wikipedia.org/wiki/ISO_3166-1"}
     */
    public static final String ISO_COUNTRY_REGEX = "^[A-Z] {2,3}$";

    private Constants() {}
}
