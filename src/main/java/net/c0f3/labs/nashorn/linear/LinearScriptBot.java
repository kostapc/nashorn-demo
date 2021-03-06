package net.c0f3.labs.nashorn.linear;

import net.c0f3.labs.storage.SimpleHashMapStorage;
import net.c0f3.labs.storage.SimpleStorage;
import net.c0f3.labs.telegram.BotEventsHandler;
import net.c0f3.labs.telegram.NashornBotCore;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

/**
 * 2018-03-12
 *
 * @author KostaPC
 * c0f3.net
 */
public class LinearScriptBot implements BotEventsHandler {

    public static void main(String[] args) {
        new NashornBotCore(
                new LinearScriptBot()
        );
    }

    private static final String SCRIPTS_FOLDER = "scripts" + File.separator + "linear";
    private static final String DEFAULT_COMMAND = "message";
    private static String[] commands = new String[]{
            "message", "time"
    };

    private final Map<String, ScriptRunner> scriptRunnerMap = new HashMap<>();
    private final SimpleStorage storage = new SimpleHashMapStorage();

    public LinearScriptBot() {
        for (String command : commands) {
            scriptRunnerMap.put(
                    command,
                    new ScriptRunner(
                            SCRIPTS_FOLDER + File.separator + command + ".js"
                    )
            );
        }
    }

    @Override
    public void onMessage(String message, String username, Consumer<String> sender) {
        LinearScriptContext callContext = new LinearScriptContext(storage, sender);
        callContext.add("username", username);
        callContext.add("message", message);
        if (message.startsWith("/")) {
            int commandEnd = message.indexOf(" ");
            if (commandEnd < 0) {
                commandEnd = message.length();
            }
            String command = message.substring(1, commandEnd);
            scriptRunnerMap.get(command).run(
                    callContext
            );
        } else {
            scriptRunnerMap.get(DEFAULT_COMMAND).run(
                    callContext
            );
        }
    }

}
