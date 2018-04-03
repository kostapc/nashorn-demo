package net.c0f3.labs.nashorn.events;

import net.c0f3.labs.nashorn.NashornWrapper;

import javax.script.Invocable;
import java.util.Map;

/**
 * 14.03.2017
 * KPC
 * Copyright (c) 2017 Infon. All rights reserved.
 */
public class ScriptCaller<T> extends NashornWrapper {

    private Invocable invocable;
    private T scriptFacade;

    public ScriptCaller(String fileName, Class<T> scriptFacadeClass, Map<String, Object> contexts) {
        super(fileName);
        invocable = compileScriptAndRun(contexts);
        scriptFacade = invocable.getInterface(scriptFacadeClass);
    }

    public T getScriptFacade() {
        return scriptFacade;
    }

}
