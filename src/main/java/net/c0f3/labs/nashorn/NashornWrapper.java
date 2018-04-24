package net.c0f3.labs.nashorn;

import javax.script.*;
import java.util.Map;
import java.util.logging.Logger;

/**
 * 2018-02-26
 *
 * @author KostaPC
 * c0f3.net
 */
public abstract class NashornWrapper {

    private static Logger logger = Logger.getLogger(NashornWrapper.class.getPackage().getName());

    public static final String ENGINE_NASHORN = "nashorn";
    public static final String SCOPE_DEFAULT_OBJECT = "app";

    private final ScriptSource scriptSource;

    public NashornWrapper(String fileName) {
        scriptSource = new ScriptSource(fileName);
    }

    protected ScriptEngine initEngine(Map<String, Object> contextObjects) {
        ScriptEngine engine = new ScriptEngineManager().getEngineByName(ENGINE_NASHORN);
        ScriptContext context = new SimpleScriptContext();
        for (Map.Entry<String, Object> entry : contextObjects.entrySet()) {
            context.setAttribute(entry.getKey(), entry.getValue(), ScriptContext.ENGINE_SCOPE);
        }
        context.setWriter(new LogOutputWriter(logger));
        engine.setContext(context);
        return engine;
    }

    protected Invocable compileScriptAndRun(Map<String, Object> contextObjects) {
        ScriptEngine scriptEngine = initEngine(contextObjects);
        try {
            scriptEngine.eval(scriptSource.getScriptFromFile());
        } catch (ScriptException e) {
            throw new IllegalArgumentException(e);
        }
        return (Invocable) scriptEngine;
    }


}
