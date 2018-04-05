package net.c0f3.labs.nashorn.app;

import jdk.nashorn.api.scripting.JSObject;
import net.c0f3.labs.storage.SimpleStorage;

import java.util.concurrent.*;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * 2018-03-22
 *
 * @author KostaPC
 * c0f3.net
 */
public class AppScriptContext {

    private final SimpleStorage storage;
    private final Executor externalExecutor = Executors.newFixedThreadPool(10);
    private final Executor jsExecutor = Executors.newSingleThreadExecutor();
    private JSObject callback;
    private final Object caller;

    public AppScriptContext(SimpleStorage storage) {
        this.storage = storage;
        this.caller = this;
    }

    void runTask(Supplier<Object> task, Consumer<Object> callback) {
        CompletableFuture.supplyAsync(task, externalExecutor).thenAccept(
                (result)-> jsExecutor.execute(() -> callback.accept(result))
        );
    }

    @SuppressWarnings("unused") // used by javascript
    public void onMessage(JSObject callback) {
        this.callback = callback;
    }

    public void save(String key, Object value, JSObject callback) {
        runTask(()-> storage.save(key, value),(res)-> callback.call(caller,res));
    }

    public void load(String key, JSObject callback) {
        runTask(()-> storage.load(key),(res)-> callback.call(caller,res));
    }

    public void delete(String key, JSObject callback) {
        runTask(()-> storage.delete(key),(res)-> callback.call(caller,res));
    }

    void processMessage(String message, String user, Consumer<String> sender) {
        runTask(()->{
            MessageHandler messageHandler = new MessageHandler(
                sender, message, user
            );
            callback.call(caller, messageHandler);
            return null;
        },(Object)->{/*nobody care*/});
    }

    public class MessageHandler {
        private final Consumer<String> sender;
        public final String msg;
        public final String user;

        public MessageHandler(Consumer<String> sender, String msg, String user) {
            this.sender = sender;
            this.msg = msg;
            this.user = user;
        }

        public void send(String outMessage, JSObject callback) {
            runTask(()-> {
                sender.accept(outMessage);
                return true; // message always send successful =)
            },(res)-> callback.call(caller, res));
        }
    }

}
