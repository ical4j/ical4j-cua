package org.ical4j.command.collection;

import net.fortuna.ical4j.model.Calendar;
import org.ical4j.connector.CalendarCollection;
import org.ical4j.connector.ObjectNotFoundException;
import org.ical4j.connector.ObjectStore;
import org.ical4j.connector.ObjectStoreException;
import picocli.CommandLine;

import java.util.function.Consumer;

@CommandLine.Command(name = "get-calendar", description = "Retrieve an existing calendar")
public class GetCalendar extends AbstractCollectionCommand<CalendarCollection, Calendar> {

    @CommandLine.Option(names = {"-uid"})
    private String calendarUid;

    public GetCalendar() {
        super();
    }

    public GetCalendar(String collectionName, Consumer<Calendar> consumer) {
        super(collectionName, consumer);
    }

    public GetCalendar(String collectionName, Consumer<Calendar> consumer, ObjectStore<Calendar, CalendarCollection> store) {
        super(collectionName, consumer, store);
    }

    public GetCalendar withCalendarUid(String calendarUid) {
        this.calendarUid = calendarUid;
        return this;
    }

    @Override
    public Integer call() {
        try {
            getConsumer().accept(getCollection().getCalendar(calendarUid));
        } catch (ObjectNotFoundException | ObjectStoreException e) {
            throw new RuntimeException(e);
        }
        return 0;
    }
}
