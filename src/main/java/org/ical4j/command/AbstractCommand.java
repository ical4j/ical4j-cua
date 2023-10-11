package org.ical4j.command;

import java.util.concurrent.Callable;
import java.util.function.Consumer;

/**
 * Base class for all commands that will invoke the specified consumer upon execution completion.
 * @param <T> the command result type
 */
public abstract class AbstractCommand<T> implements Callable<Integer> {

    /**
     * Consumer of the command result as provided by subclass implementations.
     */
    private final Consumer<T> consumer;

    /**
     * Default constructor. Prints command result to stdout.
     */
    public AbstractCommand() {
        this.consumer = DefaultOutputHandlers.STDOUT_PRINTER();
    }

    /**
     *
     * @param consumer the consumer of the command result.
     */
    public AbstractCommand(Consumer<T> consumer) {
        this.consumer = consumer;
    }

    public final Consumer<T> getConsumer() {
        return consumer;
    }
}
