package net.c0f3.labs.telegram;

import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 2018-03-04
 *
 * @author KostaPC
 * c0f3.net
 */
public class NashornDemoBot extends TelegramLongPollingBot {

    private Logger logger = Logger.getLogger(NashornDemoBot.class.getPackage().getName());

    private BotConfig botConfig = BotConfig.instance();

    public NashornDemoBot() {

    }

    @Override
    public void onUpdateReceived(Update update) {
        logger.log(Level.FINE, "some message income!: "+update.toString());
    }

    @Override
    public String getBotUsername() {
        return botConfig.getBotUsername();
    }

    @Override
    public String getBotToken() {
        return botConfig.getBotToken();
    }
}
