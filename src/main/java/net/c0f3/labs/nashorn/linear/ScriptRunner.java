package net.c0f3.labs.nashorn.linear;

import net.c0f3.labs.nashorn.NashornWrapper;

import java.util.Collections;

/**
 * 2018-02-26
 *
 * @author KostaPC
 * c0f3.net
 */
public class ScriptRunner extends NashornWrapper {

    public ScriptRunner(String fileName) {
        super(fileName);
    }

    public void run(Object context) {
        compileScriptAndRun(Collections.singletonMap(
                "bot", context
        ));
    }

}
