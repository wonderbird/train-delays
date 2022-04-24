package systems.boos.traindelays.common;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.read.ListAppender;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * In memory slf4j appender.
 * <p>
 * Convenient appender to be able to check slf4j invocations.
 * <p>
 * For usage instructions, see https://www.baeldung.com/junit-asserting-logs
 * <p>
 * The method {@link #startMemoryAppender()} creates the appender and inserts it into the logging framework.
 * <p>
 * Adopted from https://github.com/eugenp/tutorials/blob/master/testing-modules/testing-assertions/src/test/java/com/baeldung/junit/log/MemoryAppender.java
 * <p>
 * MIT License
 * <p>
 * Copyright (c) 2017 Eugen Paraschiv
 * <p>
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * <p>
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 * <p>
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
public class MemoryAppender extends ListAppender<ILoggingEvent> {

    private static final String LOGGER_NAME = "systems.boos.traindelays";

    /**
     * Create a MemoryAppender and insert it into the logging framework.
     */
    public static MemoryAppender startMemoryAppender() {
        Logger logger = (Logger) LoggerFactory.getLogger(LOGGER_NAME);
        logger.setLevel(Level.DEBUG);

        MemoryAppender memoryAppender = new MemoryAppender();
        logger.addAppender(memoryAppender);
        memoryAppender.start();

        return memoryAppender;
    }

    /**
     * Clear the list of logged events.
     */
    public void reset() {
        this.list.clear();
    }

    /**
     * Returns true only if the list contains an ILoggingEvent matching the specified content and severity level.
     */
    public boolean contains(String string, Level level) {
        return this.list.stream()
                .anyMatch(event -> event.toString().contains(string)
                        && event.getLevel().equals(level));
    }

    /**
     * Returns the number of ILoggingEvent generated by named logger.
     */
    public int countEventsForLogger(String loggerName) {
        return (int) this.list.stream()
                .filter(event -> event.getLoggerName().contains(loggerName))
                .count();
    }

    /**
     * Returns a List of ILoggingEvent matching the specific content.
     */
    public List<ILoggingEvent> search(String string) {
        return this.list.stream()
                .filter(event -> event.toString().contains(string))
                .collect(Collectors.toList());
    }

    /**
     * Returns a List of ILoggingEvent matching the specified content and severity level.
     */
    public List<ILoggingEvent> search(String string, Level level) {
        return this.list.stream()
                .filter(event -> event.toString().contains(string)
                        && event.getLevel().equals(level))
                .collect(Collectors.toList());
    }

    /**
     * Returns the number of ILoggingEvents.
     */
    public int getSize() {
        return this.list.size();
    }

    /**
     * Returns an unmodifiable view of the ILoggingEvent elements.
     */
    public List<ILoggingEvent> getLoggedEvents() {
        return Collections.unmodifiableList(this.list);
    }
}
