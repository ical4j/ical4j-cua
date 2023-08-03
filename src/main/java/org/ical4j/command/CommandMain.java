package org.ical4j.command;

import org.ical4j.command.calendar.CalendarGroup;
import org.ical4j.command.collection.CollectionGroup;
import org.ical4j.command.vcard.CardGroup;
import picocli.CommandLine;

@CommandLine.Command(name = "ical4j", description = "iCal4j Command Framework",
        subcommands = {CollectionGroup.class, CalendarGroup.class, CardGroup.class},
        mixinStandardHelpOptions = true, versionProvider = VersionProvider.class)
public class CommandMain {

    public static void main(String[] args) {
        new CommandLine(new CommandMain()).execute(args);
    }
}
