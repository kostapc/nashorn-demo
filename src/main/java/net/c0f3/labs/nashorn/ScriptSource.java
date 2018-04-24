package net.c0f3.labs.nashorn;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;

/**
 * 2018-04-05
 *
 * @author KostaPC
 * c0f3.net
 */
public class ScriptSource {

    private final String fileName;

    public ScriptSource(String fileName) {
        this.fileName = fileName;
    }

    public Reader getScriptFromFile() {
        try {
            return new FileReader(fileName);
        } catch (FileNotFoundException e) {
            throw new IllegalArgumentException("no file: " + new File(fileName).getAbsolutePath());
        }
    }

}
