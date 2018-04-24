package net.c0f3.labs.telegram;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * 2018-03-04
 *
 * @author KostaPC
 * c0f3.net
 */
public class BotConfig {

    private BotConfig() {

        Properties properties = new Properties();
        try {
            FileInputStream is = new FileInputStream(CONFIG_PATH);
            properties.load(is);
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
        botUsername = properties.getProperty("bot.username");
        botToken = properties.getProperty("bot.token");
    }

    private enum ST {
        instance(new BotConfig());

        private BotConfig config;

        ST(BotConfig config) {
            this.config = config;
        }
    }

    private static final String CONFIG_RESOURCE = "bot.properties";
    private static final String CONFIG_FOLDER = "config";
    private static final String CONFIG_PATH = CONFIG_FOLDER + File.separator + CONFIG_RESOURCE;

    private String botUsername;
    private String botToken;

    public static BotConfig instance() {
        return ST.instance.config;
    }

    public String getBotUsername() {
        return botUsername;
    }

    public String getBotToken() {
        return botToken;
    }
}
