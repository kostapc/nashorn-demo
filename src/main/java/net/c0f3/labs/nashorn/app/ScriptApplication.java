package net.c0f3.labs.nashorn.app;

import net.c0f3.labs.nashorn.NashornWrapper;

import javax.script.ScriptEngine;
import javax.script.ScriptException;
import java.util.Map;

/**
 * 2018-02-26
 *
 * @author KostaPC
 * c0f3.net
 * drived by java api - script call to java API in async mode;
 */
public class ScriptApplication extends NashornWrapper implements Runnable {

    // TODO: create implementation for callback function for async call.
    // TODO: implement events queue for callbacks and incoming events; Script must stay single-threaded and async;
    // TODO: it's necessary create special context object for async call methods from java API
    // TODO: read more about invokeFunction | invokeMethod

    private ScriptEngine script;

    public ScriptApplication(String fileName, Map<String, Object> context) {
        super(fileName);
        script = super.initEngine(context);
    }

    public void startApplication() {
        Thread scriptApplication = new Thread(this);
        scriptApplication.start();
    }

    @Override
    public void run() {
        try {
            script.eval(super.getScriptFromFile());
        } catch (ScriptException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
