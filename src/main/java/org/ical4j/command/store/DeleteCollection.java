package org.ical4j.command.store;

import org.ical4j.connector.ObjectCollection;
import org.ical4j.connector.ObjectNotFoundException;
import org.ical4j.connector.ObjectStore;
import org.ical4j.connector.ObjectStoreException;
import picocli.CommandLine;

import java.util.function.Consumer;

/**
 * A command to delete an existing collection from an object store.
 */
@CommandLine.Command(name = "delete", description = "Purge a collection")
public class DeleteCollection extends AbstractStoreCommand<ObjectCollection<?>, ObjectCollection<?>> {

    @CommandLine.Option(names = {"-name"})
    private String collectionName;

    public DeleteCollection() {
    }

    public DeleteCollection(Consumer<ObjectCollection<?>> consumer) {
        super(consumer);
    }

    public DeleteCollection(Consumer<ObjectCollection<?>> consumer, ObjectStore<?, ObjectCollection<?>> store) {
        super(consumer);
        setStore(store);
    }

    public DeleteCollection withCollectionName(String collectionName) {
        this.collectionName = collectionName;
        return this;
    }

    @Override
    public Integer call() {
        try {
            ObjectCollection<?> collection = getStore().getCollection(collectionName);
            collection.delete();
            getConsumer().accept(collection);
        } catch (ObjectStoreException | ObjectNotFoundException e) {
            throw new RuntimeException(e);
        }
        return 0;
    }
}
