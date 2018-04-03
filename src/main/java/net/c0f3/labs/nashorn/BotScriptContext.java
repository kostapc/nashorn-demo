package net.c0f3.labs.nashorn;

import net.c0f3.labs.storage.SimpleStorage;

import java.util.function.Consumer;

/**
 * 2018-03-04
 *
 * @author KostaPC
 * c0f3.net
 */
public class BotScriptContext {

    private SimpleStorage storage;
    private Consumer<String> sender;

    public BotScriptContext(SimpleStorage storage, Consumer<String> sender) {
        this.storage = storage;
        this.sender = sender;
    }

    public Object load(String key) {
        return storage.load(key);
    }

    public void save(String key, Object value) {
        storage.save(key, value);
    }

    public void send(String message) {
        sender.accept(message);
    }

    public void delete(String key) {
        storage.delete(key);
    }

}
