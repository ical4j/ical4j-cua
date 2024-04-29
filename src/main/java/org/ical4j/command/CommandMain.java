package org.ical4j.command;

import net.fortuna.ical4j.util.CompatibilityHints;
import picocli.CommandLine;

@CommandLine.Command(name = "ical4j", description = "iCal4j Command Framework",
        subcommands = {CollectionCommandGroup.class, CalendarCommandGroup.class, CardCommandGroup.class,
        TemplateCommandGroup.class, StoreCommandGroup.class, ChannelCommandGroup.class},
        mixinStandardHelpOptions = true, versionProvider = VersionProvider.class)
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
