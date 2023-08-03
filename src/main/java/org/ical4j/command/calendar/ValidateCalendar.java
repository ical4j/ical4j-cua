package org.ical4j.command.calendar;

import net.fortuna.ical4j.data.CalendarBuilder;
import net.fortuna.ical4j.data.ParserException;
import net.fortuna.ical4j.model.Calendar;
import net.fortuna.ical4j.util.Calendars;
import net.fortuna.ical4j.validate.ValidationResult;
import picocli.CommandLine;

import java.io.IOException;

@CommandLine.Command(name = "validate", description = "Produce a validation report for input data",
        subcommands = {CommandLine.HelpCommand.class})
public class ValidateCalendar extends AbstractCalendarCommand<ValidationResult> {

    @Override
    public Integer call() throws RuntimeException {
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
            ValidationResult result = cal.validate();
            if (result.hasErrors()) {
                getConsumer().accept(result);
            } else {
                System.out.print("No errors.");
            }
        } catch (IOException | ParserException e) {
            throw new RuntimeException(e);
        }
        return 0;
    }
}
