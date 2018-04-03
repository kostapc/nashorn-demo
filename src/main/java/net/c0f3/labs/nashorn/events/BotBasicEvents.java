package net.c0f3.labs.nashorn.events;

import net.c0f3.labs.nashorn.BotScriptContext;

/**
 * 2018-03-04
 *
 * @author KostaPC
 * c0f3.net
 */
public interface BotBasicEvents {

    String cmdTime(String username, BotScriptContext context);
    String cmdCities(String username, BotScriptContext context);
    String userMessage(String message, BotScriptContext context);

}
