package org.ical4j.cua;

import org.ical4j.cua.command.JettyRunCommand;
import picocli.CommandLine;

@CommandLine.Command(name = "cua", description = "iCal4j user agent",
        subcommands = {JettyRunCommand.class})
public class ICalendarUserAgentMain implements Runnable {

    @Override
    public void run() {
        System.out.println("iCal4j User Agent. Usage: cua <subcommand> [options]");
    }

    public static void main(String[] args) {
        new CommandLine(new ICalendarUserAgentMain()).execute(args);
    }
}
