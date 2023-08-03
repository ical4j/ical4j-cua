package org.ical4j.command.calendar;

import net.fortuna.ical4j.model.Calendar;
import org.ical4j.command.AbstractCollectionCommand;
import org.ical4j.connector.*;
import picocli.CommandLine;

import java.util.function.Consumer;

import static org.ical4j.connector.ObjectCollection.DEFAULT_COLLECTION;

@CommandLine.Command(name = "delete", description = "Delete an existing calendar")
public class DeleteCalendar extends AbstractCollectionCommand<CalendarCollection, Calendar> {

    @CommandLine.Option(names = {"-uid"})
    private String calendarUid;

    public DeleteCalendar() {
        super();
    }

    public DeleteCalendar(String collectionName, Consumer<Calendar> consumer) {
        super(collectionName, consumer);
    }

    public DeleteCalendar(ObjectStore<CalendarCollection> store) {
        super(DEFAULT_COLLECTION, store);
    }

    public DeleteCalendar(String collectionName, ObjectStore<CalendarCollection> store) {
        super(collectionName, store);
    }

    public DeleteCalendar withCalendarUid(String calendarUid) {
        this.calendarUid = calendarUid;
        return this;
    }

    @Override
    public Integer call() {
        try {
            getConsumer().accept(getCollection().removeCalendar(calendarUid));
        } catch (ObjectStoreException | FailedOperationException | ObjectNotFoundException e) {
            throw new RuntimeException(e);
        }
        return 0;
    }
}
