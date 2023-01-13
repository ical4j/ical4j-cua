package org.ical4j.cua;

import org.ical4j.cua.command.JettyRunCommand;
import org.ical4j.cua.command.PublishCommand;
import org.ical4j.cua.command.VersionProvider;
import picocli.CommandLine;

@CommandLine.Command(name = "cua", description = "iCal4j User Agent",
        subcommands = {PublishCommand.class, JettyRunCommand.class},
        mixinStandardHelpOptions = true, versionProvider = VersionProvider.class)
public class ICalendarUserAgentMain {

    public static void main(String[] args) {
        new CommandLine(new ICalendarUserAgentMain()).execute(args);
    }
}
