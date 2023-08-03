package org.ical4j.command.calendar;

import net.fortuna.ical4j.data.CalendarBuilder;
import net.fortuna.ical4j.data.ParserException;
import net.fortuna.ical4j.filter.ComponentFilter;
import net.fortuna.ical4j.filter.FilterExpression;
import net.fortuna.ical4j.model.Calendar;
import net.fortuna.ical4j.model.Component;
import net.fortuna.ical4j.model.ComponentList;
import net.fortuna.ical4j.model.component.CalendarComponent;
import net.fortuna.ical4j.util.Calendars;
import org.ical4j.connector.CalendarCollection;
import org.ical4j.connector.ObjectStore;
import picocli.CommandLine;

import java.io.IOException;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static org.ical4j.command.DefaultOutputHandlers.STDOUT_LIST_PRINTER;
import static org.ical4j.connector.ObjectCollection.DEFAULT_COLLECTION;

@CommandLine.Command(name = "calendar-collection", description = "Query a specified calendar collection using filter expressions")
public class FilterCalendars extends AbstractCalendarCommand<List<Calendar>> {

    @CommandLine.Option(names = {"-query"})
    private String query;

    public FilterCalendars() {
        super(DEFAULT_COLLECTION, STDOUT_LIST_PRINTER());
    }

    public FilterCalendars(String collectionName, Consumer<List<Calendar>> consumer) {
        super(collectionName, consumer);
    }

    public FilterCalendars(String collectionName, ObjectStore<CalendarCollection> store) {
        super(collectionName, store);
    }

    @Override
    public Integer call() {
        try {
            Calendar cal = null;
            if (input.filename != null) {
                cal = Calendars.load(input.filename);
            } else if (input.url != null) {
                cal = Calendars.load(input.url);
            } else if (input.stdin) {
                final CalendarBuilder builder = new CalendarBuilder();
                cal = builder.build(System.in);
            }
            Predicate<Component> filter;
            if (query != null && !query.isEmpty()) {
                filter = new ComponentFilter<>().predicate(FilterExpression.parse(query));
            } else {
                filter = (it) -> true;
            }
            List<CalendarComponent> filtered = cal.getComponents().stream().filter(filter).collect(Collectors.toList());

            System.out.print(new Calendar(new ComponentList<>(filtered)));
        } catch (IOException | ParserException e) {
            throw new RuntimeException(e);
        }
        return 0;
    }
}
