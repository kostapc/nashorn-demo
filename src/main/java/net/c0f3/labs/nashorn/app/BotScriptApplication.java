package net.c0f3.labs.nashorn.app;

import net.c0f3.labs.nashorn.events.EventsDrivenBot;
import net.c0f3.labs.storage.SimpleHashMapStorage;
import net.c0f3.labs.storage.SimpleStorage;
import net.c0f3.labs.telegram.BotEventsHandler;
import net.c0f3.labs.telegram.NashornBotCore;

import java.io.File;
import java.util.Collections;
import java.util.function.Consumer;

/**
 * 2018-03-22
 *
 * @author KostaPC
 * c0f3.net
 */
public class BotScriptApplication implements BotEventsHandler {

    public static void main(String[] args) {
        BotScriptApplication botApp = new BotScriptApplication();
        botApp.start();
        new NashornBotCore(
                botApp
        );
    }

    private final SimpleStorage storage;
    private final AppScriptContext context;
    private final ScriptApplication script;

    public BotScriptApplication() {
        storage = new SimpleHashMapStorage();
        context = new AppScriptContext(storage);
        script = new ScriptApplication(
            "scripts"+ File.separator+"app"+File.separator+"bot.js",
             Collections.singletonMap("bot", context)
        );
    }

    public void start() {
        script.startApplication();
    }

    @Override
    public void onMessage(String message, String username, Consumer<String> sender) {
        context.processMessage(message, username, sender);
    }
}
