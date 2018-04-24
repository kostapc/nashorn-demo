package net.c0f3.labs.nashorn.linear;

import net.c0f3.labs.nashorn.NashornWrapper;
import net.c0f3.labs.nashorn.app.BotScriptApplication;

import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 2018-02-26
 *
 * @author KostaPC
 * c0f3.net
 */
public class ScriptRunner extends NashornWrapper {

    private static Logger logger = Logger.getLogger(LinearScriptBot.class.getPackage().getName());

    public ScriptRunner(String fileName) {
        super(fileName);
    }

    public void run(Object context) {
        long nanos = System.nanoTime();
        compileScriptAndRun(Collections.singletonMap(
                "bot", context
        ));
        logger.log(Level.FINE, "LinearScriptBot script call time: "+(System.nanoTime()-nanos));
    }

}
