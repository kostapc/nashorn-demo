package net.c0f3.labs.nashorn;

import java.io.IOException;
import java.io.Writer;
import java.util.logging.Logger;


/**
 * @author KostaPC
 *
 * <a href="https://web.archive.org/web/20130527080241/http://www.java2s.com/Open-Source/Java/Testing/jacareto/jacareto/toolkit/log4j/LogOutputStream.java.htm">original sources</a> and
 * <a href="http://stackoverflow.com/questions/6995946/log4j-how-do-i-redirect-an-outputstream-or-writer-to-loggers-writers">discuss</a>*
 */
public class LogOutputWriter extends Writer {
    /**
     * The logger where to log the written bytes.
     */
    private Logger logger;


    /**
     * The internal memory for the written bytes.
     */
    private StringBuilder writer;

    /**
     * Creates a new log output stream which logs bytes to the specified logger with the specified
     * level.
     *
     * @param logger the logger where to log the written bytes
     */
    public LogOutputWriter(Logger logger) {
        this.logger = logger;
        this.writer = new StringBuilder();
    }


    /**
     * Returns the logger.
     *
     * @return this writer logger!
     */
    public Logger getLogger() {
        return logger;
    }

    /**
     * Writes a byte to the output stream. This method flushes automatically at the end of a line.
     *
     * @param b - byte of data
     */
    @Override
    public void write(int b) {
        if (b == '\n') {
            flush();
            clear();
            return;
        }
        writer.append(b);
    }

    /**
     * Flushes the output stream.
     */
    @Override
    public void flush() {
        if (writer.length() > 0) {
            String output = writer.toString();
            logger.info(output);
        }
    }

    private void clear() {
        writer = new StringBuilder();
    }

    @Override
    public void write(char[] bytes, int off, int len) throws IOException {
        char[] buf = new char[len];
        System.arraycopy(bytes, off, buf, 0, len);
        write(buf);
    }

    @Override
    public void write(char[] cbuf) throws IOException {
        for (char ch : cbuf) {
            if (ch == '\n') {
                flush();
                clear();
                continue;
            }
            writer.append(ch);
        }
    }

    @Override
    public void write(String str) throws IOException {
        write(str.toCharArray());
    }

    @Override
    public void write(String str, int off, int len) throws IOException {
        write(str.toCharArray(), off, len);
    }

    @Override
    public Writer append(CharSequence csq) throws IOException {
        char[] chars = new char[csq.length()];
        for (int i = 0; i < csq.length(); i++) {
            chars[i] = csq.charAt(i);
        }
        write(chars);
        return this;
    }

    @Override
    public Writer append(CharSequence csq, int start, int end) throws IOException {
        return append(csq.subSequence(start, end));
    }

    @Override
    public Writer append(char c) throws IOException {
        write(c);
        return this;
    }

    @Override
    public void close() throws IOException {

    }
}
