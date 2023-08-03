package org.ical4j.command.calendar;

import net.fortuna.ical4j.data.ParserException;
import net.fortuna.ical4j.model.ConstraintViolationException;
import net.fortuna.ical4j.model.property.Uid;
import org.ical4j.connector.CalendarCollection;
import org.ical4j.connector.CalendarStore;
import org.ical4j.connector.ObjectNotFoundException;
import org.ical4j.connector.ObjectStoreException;
import picocli.CommandLine;

import java.io.IOException;
import java.util.function.Consumer;

import static org.ical4j.connector.ObjectCollection.DEFAULT_COLLECTION;

/**
 * Create a new calendar in the specified collection using provided calendar data. The calendar data should
 * include only components of a single type and with the same UID.
 * <p>
 * The required inputs for this command are:
 * * collectionName -> the collection to add the calendar to
 * * calendarData -> data used to construct an iCalendar object
 * * outputHandler -> defines how output is handled on successful command completion
 * <p>
 * The outputs for this command are:
 * * uid -> the UID of the calendar that was successfully added to the collection
 * <p>
 * Command configuration options include:
 * * defaultCollection -> the default collection used if no collectionName is specified
 * * defaultOutputHandler -> the default output handler if none is specified
 * * generateUid -> whether to create a UID for the calendar if none exists
 * * overrideUid -> always replace any existing UID with an internally generated one (implies generateUID = true)
 * <p>
 * To import multiple different components use {@link ImportCalendars}.
 */
@CommandLine.Command(name = "create", description = "Create a new calendar")
public class CreateCalendar extends AbstractCalendarCommand<Uid> {

    public CreateCalendar() {
        super(DEFAULT_COLLECTION);
    }


    public CreateCalendar(CalendarStore<CalendarCollection> store) {
        super(DEFAULT_COLLECTION, store);
    }

    public CreateCalendar(String collectionName, Consumer<Uid> consumer) {
        super(collectionName, consumer);
    }

    public CreateCalendar(String collectionName, CalendarStore<CalendarCollection> store) {
        super(collectionName, store);
    }

    @Override
    public Integer call() {
        try {
            getConsumer().accept(getCollection().addCalendar(getCalendar()));
        } catch (ObjectStoreException | ConstraintViolationException | ObjectNotFoundException | ParserException |
                 IOException e) {
            throw new RuntimeException(e);
        }
        return 0;
    }
}
