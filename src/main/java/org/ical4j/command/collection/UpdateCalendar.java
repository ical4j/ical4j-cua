package org.ical4j.command.collection;

import net.fortuna.ical4j.data.ParserException;
import net.fortuna.ical4j.model.Calendar;
import net.fortuna.ical4j.model.property.Uid;
import org.ical4j.command.InputOptions;
import org.ical4j.command.calendar.FilterCalendar;
import org.ical4j.connector.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import picocli.CommandLine;

import java.io.IOException;
import java.util.function.Consumer;

import static org.ical4j.connector.ObjectCollection.DEFAULT_COLLECTION;

@CommandLine.Command(name = "update-calendar", description = "Update an existing calendar")
public class UpdateCalendar extends AbstractCollectionCommand<CalendarCollection, Uid[]> {

    private static final Logger LOGGER = LoggerFactory.getLogger(UpdateCalendar.class);

    @CommandLine.ArgGroup(multiplicity = "1")
    protected InputOptions input;

    private Calendar calendar;

    public UpdateCalendar() {
        super();
    }

    public UpdateCalendar(String collectionName, Consumer<Uid[]> consumer) {
        super(collectionName, consumer);
    }

    public UpdateCalendar(ObjectStore<Calendar, CalendarCollection> store) {
        super(DEFAULT_COLLECTION, store);
    }

    public UpdateCalendar(String collectionName, ObjectStore<Calendar, CalendarCollection> store) {
        super(collectionName, store);
    }

    public UpdateCalendar withCalendar(Calendar calendar) {
        this.calendar = calendar;
        return this;
    }

    public Calendar getCalendar() throws ParserException, IOException {
        if (calendar == null) {
            calendar = input.toCalendar();
        }
        return calendar;
    }

    @Override
    public Integer call() {
        try {
            getCollection().merge(getCalendar());
        } catch (ObjectStoreException | FailedOperationException | ObjectNotFoundException | ParserException |
                 IOException e) {
            LOGGER.error("Unexpected error", e);
            return 1;
        }
        return 0;
    }
}
