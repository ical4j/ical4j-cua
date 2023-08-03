package org.ical4j.command;

import org.ical4j.integration.ChannelAdapter;

import java.util.function.Consumer;

/**
 * Subclasses provide functionality that requires data store connectivity.
 *
 * @param <T> the supported collection type for a configured data store
 * @param <R> the command result consumer
 */
public abstract class AbstractChannelCommand<T, R> extends AbstractCommand<R> {

    private final ChannelAdapter<T> endpoint;

    public AbstractChannelCommand() {
        this.endpoint = null;
    }

    public AbstractChannelCommand(ChannelAdapter<T> endpoint) {
        this.endpoint = endpoint;
    }

    public AbstractChannelCommand(Consumer<R> consumer) {
        super(consumer);
        this.endpoint = null;
    }

    public AbstractChannelCommand(Consumer<R> consumer, ChannelAdapter<T> endpoint) {
        super(consumer);
        this.endpoint = endpoint;
    }

    public ChannelAdapter<T> getEndpoint() {
        return endpoint;
    }
}
