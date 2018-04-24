package net.c0f3.labs.storage;

import java.util.concurrent.ConcurrentHashMap;

/**
 * 2018-03-12
 *
 * @author KostaPC
 * c0f3.net
 */
public class SimpleHashMapStorage implements SimpleStorage {
    private ConcurrentHashMap<String, Object> map = new ConcurrentHashMap<>();

    @Override
    public Boolean save(String key, Object value) {
        map.put(key, value);
        return true;
    }

    @Override
    public Object load(String key) {
        return map.get(key);
    }

    @Override
    public Boolean delete(String key) {
        return map.remove(key) != null;
    }
}
