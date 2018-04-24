package net.c0f3.labs.nashorn.linear;

import jdk.nashorn.api.scripting.JSObject;
import net.c0f3.labs.nashorn.BotScriptContext;
import net.c0f3.labs.storage.SimpleStorage;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * 2018-03-15
 *
 * @author KostaPC
 * c0f3.net
 */
public class LinearScriptContext implements JSObject {

    private final BotScriptContext simpleScriptContext;
    private final Map<String, Object> values = new HashMap<>();

    public LinearScriptContext(SimpleStorage storage, Consumer<String> sender) {
        simpleScriptContext = new BotScriptContext(storage, sender);
        values.put(
                "load",
                (Function<String, Object>) simpleScriptContext::load
        );
        values.put(
                "save",
                (BiConsumer<String, Object>) simpleScriptContext::save
        );
        values.put(
                "send",
                (Consumer<Object>) (msg) -> simpleScriptContext.send(msg.toString())
        );
        values.put(
                "delete",
                (Consumer<String>) simpleScriptContext::delete
        );
    }

    void add(String key, Object value) {
        values.put(key, value);
    }

    @Override
    public Object call(Object o, Object... objects) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Object newObject(Object... objects) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Object eval(String s) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Object getMember(String s) {
        return values.get(s);
    }

    @Override
    public Object getSlot(int i) {
        // TODO: iterate and return object with specified index;
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean hasMember(String s) {
        return values.containsKey(s);
    }

    @Override
    public boolean hasSlot(int i) {
        // TODO: iterate and return object with specified index;
        throw new UnsupportedOperationException();
    }

    @Override
    public void removeMember(String s) {
        values.remove(s);
    }

    @Override
    public void setMember(String s, Object o) {
        values.put(s, o);
    }

    @Override
    public void setSlot(int i, Object o) {
        // TODO: iterate and return object with specified index;
        throw new UnsupportedOperationException();
    }

    @Override
    public Set<String> keySet() {
        return values.keySet();
    }

    @Override
    public Collection<Object> values() {
        return values.values();
    }

    @Override
    public boolean isInstance(Object o) {
        return o == this;
    }

    @Override
    public boolean isInstanceOf(Object o) {
        return (o instanceof LinearScriptContext);
    }

    @Override
    public String getClassName() {
        return LinearScriptContext.class.getName();
    }

    @Override
    public boolean isFunction() {
        return false;
    }

    @Override
    public boolean isStrictFunction() {
        return false;
    }

    @Override
    public boolean isArray() {
        return false;
    }

    @Override
    public double toNumber() {
        return 0;
    }
}
