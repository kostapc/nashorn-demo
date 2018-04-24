package net.c0f3.labs.telegram;

import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 2018-03-12
 *
 * @author KostaPC
 * c0f3.net
 */
public class NashornBot extends TelegramLongPollingBot {

    private Logger logger = Logger.getLogger(NashornDemoBot.class.getPackage().getName());

    private BotConfig botConfig = BotConfig.instance();
    private BotEventsHandler eventsHandler;

    public NashornBot(BotEventsHandler handler) {
        eventsHandler = handler;
    }

    @Override
    public void onUpdateReceived(Update update) {
        eventsHandler.onMessage(
                update.getMessage().getText(),
                String.valueOf(update.getMessage().getChatId()),
                (msg) -> {
                    SendMessage tgMessage = new SendMessage()
                            .setChatId(update.getMessage().getChatId())
                            .setText(msg);
                    try {
                        execute(tgMessage);
                    } catch (TelegramApiException e) {
                        logger.log(Level.WARNING, e.getMessage());
                    }
                }
        );
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
