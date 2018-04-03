package net.c0f3.labs.nashorn.events;

import net.c0f3.labs.nashorn.BotScriptContext;
import net.c0f3.labs.storage.SimpleHashMapStorage;
import net.c0f3.labs.storage.SimpleStorage;
import net.c0f3.labs.telegram.BotEventsHandler;
import net.c0f3.labs.telegram.NashornBotCore;

import java.io.File;
import java.util.Collections;
import java.util.function.Consumer;

/**
 * 2018-03-12
 *
 * @author KostaPC
 * c0f3.net
 */
public class EventsDrivenBot implements BotEventsHandler {

    public static void main(String[] args) {
        new NashornBotCore(
            new EventsDrivenBot()
        );
    }

    private BotBasicEvents botScript;
    private SimpleStorage storage = new SimpleHashMapStorage();

    public EventsDrivenBot() {

        ScriptCaller<BotBasicEvents> caller = new ScriptCaller<>(
            "scripts"+ File.separator+"events"+File.separator+"handler.js",
            BotBasicEvents.class, Collections.emptyMap()
        );

        botScript = caller.getScriptFacade();
    }

    @Override
    public void onMessage(String message, String username, Consumer<String> sender) {
        BotScriptContext callContext = new BotScriptContext(storage,sender);
        if(message.startsWith("/time")) {
            botScript.cmdTime(username, callContext);
            return;
        }
        if(message.startsWith("/cities")) {
            botScript.cmdCities(username, callContext);
            return;
        }
        botScript.userMessage(message, callContext);
    }

}
