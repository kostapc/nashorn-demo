package net.c0f3.labs.nashorn;

import javax.script.Compilable;
import javax.script.ScriptEngine;
import javax.script.ScriptException;
import java.io.Reader;

/**
 * 2018-02-26
 *
 * @author KostaPC
 * c0f3.net
 */
public class ScriptObject {

    // http://mail.openjdk.java.net/pipermail/nashorn-dev/2014-October/003762.html
    // TODO: benchmark this

    private ScriptEngine engine;
    private Reader reader;

    ScriptObject(ScriptEngine engine, Reader reader) {
        this.engine = engine;
    }

    public void compile() {
        try {
            Compilable compilable = (Compilable) engine;
            compilable.compile(reader);
        } catch (ScriptException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public void execute() {
        try {
            engine.eval(reader);
        } catch (ScriptException e) {
            throw new IllegalArgumentException(e);
        }
    }

}
