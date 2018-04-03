package net.c0f3.labs.telegram;

import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.logging.LogManager;

/**
 * 2018-03-04
 *
 * @author KostaPC
 * c0f3.net
 */
public class NashornBotCore {

    static {
        ApiContextInitializer.init();
        try {
            LogManager.getLogManager().readConfiguration(
                    new FileInputStream("config"+ File.separator+"logger.properties")
            );
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    public NashornBotCore(BotEventsHandler eventsHandler) {
        TelegramBotsApi botsApi = new TelegramBotsApi();
        try {
            botsApi.registerBot(new NashornBot(
                eventsHandler
            ));
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    // demo bot start
    public static void main(String[] args) {

        TelegramBotsApi botsApi = new TelegramBotsApi();

        try {
            botsApi.registerBot(new NashornDemoBot());
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
