package org.ical4j.command.store;

import org.ical4j.command.calendar.FilterCalendar;
import org.ical4j.connector.ObjectCollection;
import org.ical4j.connector.ObjectNotFoundException;
import org.ical4j.connector.ObjectStore;
import org.ical4j.connector.ObjectStoreException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import picocli.CommandLine;

import java.util.List;
import java.util.function.Consumer;

@CommandLine.Command(name = "list-collections", description = "List collections in an object store")
public class ListCollections<T extends ObjectCollection<?>> extends AbstractStoreCommand<T, List<T>> {

    private static final Logger LOGGER = LoggerFactory.getLogger(ListCollections.class);

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
            LOGGER.error("Unexpected error", e);
            return 1;
        }
        return 0;
    }
}
