package org.ical4j.command;

import org.ical4j.command.template.EventCalendar;
import picocli.CommandLine;

@CommandLine.Command(name = "template", description = "Template calendar and card operations",
        subcommands = {EventCalendar.class, })
public class TemplateCommandGroup {

}
