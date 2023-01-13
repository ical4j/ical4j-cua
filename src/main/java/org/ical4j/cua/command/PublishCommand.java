package org.ical4j.cua.command;

import net.fortuna.ical4j.agent.VEventUserAgent;
import net.fortuna.ical4j.data.CalendarBuilder;
import net.fortuna.ical4j.data.ParserException;
import net.fortuna.ical4j.model.Calendar;
import net.fortuna.ical4j.model.component.VEvent;
import net.fortuna.ical4j.model.property.Organizer;
import net.fortuna.ical4j.model.property.ProdId;
import net.fortuna.ical4j.util.Calendars;
import net.fortuna.ical4j.util.RandomUidGenerator;
import picocli.CommandLine;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

@CommandLine.Command(name = "publish", description = "Publish an event via iTIP calendar object format")
public class PublishCommand extends AbstractCommand {

    @Override
    public void run() {
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
            VEventUserAgent userAgent = new VEventUserAgent(new ProdId("iCal4j User Agent"),
                    new Organizer("johnd@example.com"), new RandomUidGenerator());

            List<VEvent> events = cal.getComponents("VEVENT");
            System.out.print(userAgent.publish(events.toArray(new VEvent[0])));
        } catch (IOException | ParserException | URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }
}
