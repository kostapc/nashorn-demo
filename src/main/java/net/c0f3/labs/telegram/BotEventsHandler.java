package net.c0f3.labs.telegram;

import java.util.function.Consumer;

/**
 * 2018-03-12
 *
 * @author KostaPC
 * c0f3.net
 */
public interface BotEventsHandler {

    void onMessage(String message, String username, Consumer<String> sender);

}
