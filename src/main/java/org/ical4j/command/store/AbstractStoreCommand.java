package org.ical4j.command.store;

import org.ical4j.command.AbstractCommand;
import org.ical4j.connector.ObjectCollection;
import org.ical4j.connector.ObjectStore;

import java.util.function.Consumer;

/**
 * Subclasses provide functionality that requires data store connectivity.
 *
 * @param <T> the supported collection type for a configured data store
 * @param <R> the command result consumer
 */
public abstract class AbstractStoreCommand<T extends ObjectCollection<?>, R> extends AbstractCommand<R> {

    private ObjectStore<?, T> store;

    public AbstractStoreCommand() {
    }

    public AbstractStoreCommand(Consumer<R> consumer) {
        super(consumer);
    }

    public ObjectStore<?, T> getStore() {
        return store;
    }

    public void setStore(ObjectStore<?, T> store) {
        this.store = store;
    }
}
