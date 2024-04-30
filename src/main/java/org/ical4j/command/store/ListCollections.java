package org.ical4j.command.store;

import org.ical4j.connector.ObjectCollection;
import org.ical4j.connector.ObjectNotFoundException;
import org.ical4j.connector.ObjectStore;
import org.ical4j.connector.ObjectStoreException;
import picocli.CommandLine;

import java.util.List;
import java.util.function.Consumer;

@CommandLine.Command(name = "list-collections", description = "List collections in an object store")
public class ListCollections<T extends ObjectCollection<?>> extends AbstractStoreCommand<T, List<T>> {

    public ListCollections() {
    }

    public ListCollections(Consumer<List<T>> consumer) {
        super(consumer);
    }

    public ListCollections(Consumer<List<T>> consumer, ObjectStore<?, T> store) {
        super(consumer);
        setStore(store);
    }

    @Override
    public Integer call() {
        try {
            getConsumer().accept(getStore().getCollections());
        } catch (ObjectStoreException | ObjectNotFoundException e) {
            throw new RuntimeException(e);
        }
        return 0;
    }
}
