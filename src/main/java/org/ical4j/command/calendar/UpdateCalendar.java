package org.ical4j.command.calendar;

import net.fortuna.ical4j.data.ParserException;
import net.fortuna.ical4j.model.property.Uid;
import org.ical4j.connector.*;
import picocli.CommandLine;

import java.io.IOException;
import java.util.function.Consumer;

import static org.ical4j.connector.ObjectCollection.DEFAULT_COLLECTION;

@CommandLine.Command(name = "update", description = "Update an existing calendar")
public class UpdateCalendar extends AbstractCalendarCommand<Uid[]> {

    public UpdateCalendar() {
        super();
    }

    public UpdateCalendar(String collectionName, Consumer<Uid[]> consumer) {
        super(collectionName, consumer);
    }

    public UpdateCalendar(ObjectStore<CalendarCollection> store) {
        super(DEFAULT_COLLECTION, store);
    }

    public UpdateCalendar(String collectionName, ObjectStore<CalendarCollection> store) {
        super(collectionName, store);
    }

    @Override
    public Integer call() {
        try {
            getCollection().merge(getCalendar());
        } catch (ObjectStoreException | FailedOperationException | ObjectNotFoundException | ParserException |
                 IOException e) {
            throw new RuntimeException(e);
        }
        return 0;
    }
}
