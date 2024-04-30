package org.ical4j.command;

import net.fortuna.ical4j.util.CompatibilityHints;
import picocli.CommandLine;

@CommandLine.Command(name = "ical4j", description = "iCal4j Command Framework",
        subcommands = {CalendarCommandGroup.class, CardCommandGroup.class, CollectionCommandGroup.class,
                ChannelCommandGroup.class, StoreCommandGroup.class, TemplateCommandGroup.class,
        ConfigureCommand.class},
        scope = CommandLine.ScopeType.INHERIT, mixinStandardHelpOptions = true, versionProvider = VersionProvider.class,
        footer = "Copyright (c) Ben Fortuna", showAtFileInUsageHelp = true)
public class CommandMain {

    @CommandLine.Option(names = "-lenient", description = "Enable lenient parsing", scope = CommandLine.ScopeType.INHERIT)
    public void setRelaxedParsing(boolean relaxedParsing) {
        CompatibilityHints.setHintEnabled(CompatibilityHints.KEY_RELAXED_PARSING, true);
        CompatibilityHints.setHintEnabled(CompatibilityHints.KEY_RELAXED_UNFOLDING, true);
    }

    public static void main(String[] args) {
        int exitCode = new CommandLine(new CommandMain()).execute(args);
        System.exit(exitCode);
    }
}
