package org.ical4j.command.calendar;

import net.fortuna.ical4j.data.ParserException;
import net.fortuna.ical4j.filter.ComponentFilter;
import net.fortuna.ical4j.filter.FilterExpression;
import net.fortuna.ical4j.model.Component;
import net.fortuna.ical4j.model.component.CalendarComponent;
import picocli.CommandLine;

import java.io.IOException;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static org.ical4j.command.DefaultOutputHandlers.STDOUT_LIST_PRINTER;

@CommandLine.Command(name = "filter", description = "Query a specified calendar collection using filter expressions")
public class FilterCalendar extends AbstractCalendarCommand<List<CalendarComponent>> {

    @CommandLine.Option(names = {"-query"})
    private String query;

    public FilterCalendar() {
        super(STDOUT_LIST_PRINTER());
    }

    @Override
    public Integer call() {
        try {
            Predicate<Component> filter;
            if (query != null && !query.isEmpty()) {
                filter = new ComponentFilter<>().predicate(FilterExpression.parse(query));
            } else {
                filter = (it) -> true;
            }
            List<CalendarComponent> filtered = getCalendar().getComponents().stream().filter(filter).collect(Collectors.toList());
            getConsumer().accept(filtered);
        } catch (IOException | ParserException e) {
            throw new RuntimeException(e);
        }
        return 0;
    }
}
