package org.ical4j.command;

import picocli.CommandLine;

@CommandLine.Command(name = "store", description = "Command group for store operations",
        subcommands = {},
        mixinStandardHelpOptions = true)
public class StoreCommandGroup {

}
