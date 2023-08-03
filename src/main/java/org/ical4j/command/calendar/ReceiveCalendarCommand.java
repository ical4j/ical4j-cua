package org.ical4j.command.calendar;

import net.fortuna.ical4j.model.Calendar;
import org.ical4j.command.AbstractChannelCommand;
import picocli.CommandLine;

import java.util.function.Consumer;

@CommandLine.Command(name = "receive", description = "Receive a calendar from a channel")
public class ReceiveCalendarCommand extends AbstractChannelCommand<Calendar, Calendar> {

    private long timeout;

    private boolean autoExpunge;

    public ReceiveCalendarCommand(Consumer<Calendar> consumer) {
        super(consumer);
    }

    @Override
    public Integer call() {
        boolean success = getEndpoint().receive(getConsumer(), timeout, autoExpunge);
        return success ? 0 : 1;
    }
}
