package org.ical4j.command.calendar;

import net.fortuna.ical4j.data.ParserException;
import net.fortuna.ical4j.validate.ValidationResult;
import org.ical4j.command.DefaultOutputHandlers;
import picocli.CommandLine;

import java.io.IOException;
import java.io.PrintWriter;

@CommandLine.Command(name = "validator", description = "Produce a validation report for input data",
        subcommands = {CommandLine.HelpCommand.class})
public class Validator extends AbstractCalendarCommand<ValidationResult> {

    public Validator() {
        super(DefaultOutputHandlers.STDOUT_REPORT_PRINTER(new PrintWriter(System.out, true)));
    }

    @Override
    public Integer call() throws RuntimeException {
        try {
            ValidationResult result = getCalendar().validate();
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
