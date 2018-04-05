package net.c0f3.labs.nashorn.app;

import net.c0f3.labs.nashorn.NashornWrapper;
import net.c0f3.labs.nashorn.ScriptSource;

import javax.script.Compilable;
import javax.script.CompiledScript;
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

    // TODO: read more about invokeFunction | invokeMethod

    private final ScriptSource scriptSource;
    private final ScriptEngine script;

    public ScriptApplication(String fileName, Map<String, Object> context) {
        super(fileName);
        this.scriptSource = new ScriptSource(fileName);
        this.script = super.initEngine(context);
    }

    public void startApplication() {
        Thread scriptApplication = new Thread(this);
        scriptApplication.start();
    }

    @Override
    public void run() {
        try {
            // TODO: move compiled invoke to wrapper
            CompiledScript compiledScript = ((Compilable)script).compile(
                    scriptSource.getScriptFromFile()
            );
            compiledScript.eval(script.getContext());
        } catch (ScriptException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
