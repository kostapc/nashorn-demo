package net.c0f3.labs.storage;

/**
 * 2018-03-12
 *
 * @author KostaPC
 * c0f3.net
 */
public interface SimpleStorage {
    Boolean save(String key, Object value);
    Object load(String key);
    Boolean delete(String key);
}
