package org.ical4j.command.vcard;

import picocli.CommandLine;

@CommandLine.Command(name = "card", description = "Command group for card operations",
        subcommands = {GetCard.class, ListCards.class,
        CreateCard.class, UpdateCard.class,
                DeleteCard.class, SendVCardCommand.class, ReceiveVCardCommand.class},
        mixinStandardHelpOptions = true)
public class CardGroup {

}
