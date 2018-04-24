package net.c0f3.labs.nashorn.app;

import net.c0f3.labs.storage.SimpleHashMapStorage;
import net.c0f3.labs.storage.SimpleStorage;
import org.junit.Test;

import java.io.File;
import java.util.Collections;
import java.util.UUID;

/**
 * 2018-03-30
 *
 * @author KostaPC
 * c0f3.net
 */
public class AppScriptContextTest {

    public static void main(String[] args) {
        new AppScriptContextTest().processMessageTest();
    }

    @Test
    public void processMessageTest() {

        SimpleStorage storage;
        AppScriptContext context;
        ScriptApplication script;

        storage = new SimpleHashMapStorage();
        context = new AppScriptContext(storage);
        script = new ScriptApplication(
                "scripts" + File.separator + "app" + File.separator + "bot.js",
                Collections.singletonMap("bot", context)
        );
        script.startApplication();
        new Thread(() -> {
            int i = 0;
            int rc = 0;
            while (!Thread.currentThread().isInterrupted()) {
                context.processMessage(
                        "some other message (id:" + UUID.randomUUID() + ")", "user-" + i,
                        System.out::println
                );
                context.processMessage(
                        "/time", "user-" + i,
                        System.out::println
                );
                if (++rc % 2 == 0) {
                    i++;
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    return;
                }
            }
        }).start();
    }
}