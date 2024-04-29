package org.ical4j.command;

import org.ical4j.command.channel.ReceiveVCardCommand;
import org.ical4j.command.channel.SendVCardCommand;
import org.ical4j.command.collection.*;
import picocli.CommandLine;

@CommandLine.Command(name = "card", description = "Command group for card operations",
        subcommands = {GetCard.class, ListCards.class,
        CreateCard.class, UpdateCard.class,
                DeleteCard.class, SendVCardCommand.class, ReceiveVCardCommand.class},
        mixinStandardHelpOptions = true)
public class CardCommandGroup {

}
