package org.ical4j.command;

import org.ical4j.command.template.EventCalendar;
import org.ical4j.command.calendar.FilterCalendar;
import org.ical4j.command.calendar.ReplaceUids;
import org.ical4j.command.calendar.Validator;
import org.ical4j.command.channel.ReceiveCalendarCommand;
import org.ical4j.command.channel.SendCalendarCommand;
import org.ical4j.command.collection.*;
import picocli.CommandLine;

@CommandLine.Command(name = "calendar", description = "Command group for calendar operations",
        subcommands = {EventCalendar.class, GetCalendar.class, ListCalendars.class,
        CreateCalendar.class, UpdateCalendar.class,
                DeleteCalendar.class, SendCalendarCommand.class,
                ReceiveCalendarCommand.class, FilterCalendar.class,
        Validator.class, ReplaceUids.class},
        mixinStandardHelpOptions = true)
public class CalendarCommandGroup {

}
