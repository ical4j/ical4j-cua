package org.ical4j.command.calendar;

import net.fortuna.ical4j.model.Calendar;
import org.ical4j.command.AbstractChannelCommand;
import picocli.CommandLine;

@CommandLine.Command(name = "send", description = "Send a calendar on a channel")
public class SendCalendarCommand extends AbstractChannelCommand<Calendar, Void> {

    private Calendar calendar;

    public SendCalendarCommand withCalendar(Calendar calendar) {
        this.calendar = calendar;
        return this;
    }

    @Override
    public Integer call() {
        boolean success = getEndpoint().send(() -> calendar);
        return success ? 0 : 1;
    }
}
