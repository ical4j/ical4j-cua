package org.ical4j.command.calendar;

import picocli.CommandLine;

@CommandLine.Command(name = "calendar", description = "Command group for calendar operations",
        subcommands = {GetCalendar.class, ListCalendars.class,
        CreateCalendar.class, UpdateCalendar.class,
                DeleteCalendar.class, SendCalendarCommand.class, ReceiveCalendarCommand.class},
        mixinStandardHelpOptions = true)
public class CalendarGroup {

}
