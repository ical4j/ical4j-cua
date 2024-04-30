package org.ical4j.command.store;

import org.ical4j.command.calendar.FilterCalendar;
import org.ical4j.connector.ObjectCollection;
import org.ical4j.connector.ObjectNotFoundException;
import org.ical4j.connector.ObjectStore;
import org.ical4j.connector.ObjectStoreException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import picocli.CommandLine;

import java.util.function.Consumer;

@CommandLine.Command(name = "get-collection-details", description = "Retrieve a collection")
public class GetCollectionDetails extends AbstractStoreCommand<ObjectCollection<?>, ObjectCollection<?>> {

    private static final Logger LOGGER = LoggerFactory.getLogger(GetCollectionDetails.class);

    @CommandLine.Option(names = {"-name"})
    private String collectionName;

    public GetCollectionDetails() {
    }

    public GetCollectionDetails(Consumer<ObjectCollection<?>> consumer) {
        super(consumer);
    }

    public GetCollectionDetails(Consumer<ObjectCollection<?>> consumer, ObjectStore<?, ObjectCollection<?>> store) {
        super(consumer);
        setStore(store);
    }

    public GetCollectionDetails withCollectionName(String collectionName) {
        this.collectionName = collectionName;
        return this;
    }

    @Override
    public Integer call() {
        try {
            getConsumer().accept(getStore().getCollection(collectionName));
        } catch (ObjectStoreException | ObjectNotFoundException e) {
            LOGGER.error("Unexpected error", e);
            return 1;
        }
        return 0;
    }
}
