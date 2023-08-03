package org.ical4j.command.vcard;

import net.fortuna.ical4j.vcard.VCard;
import org.ical4j.command.AbstractChannelCommand;
import picocli.CommandLine;

import java.util.function.Consumer;

@CommandLine.Command(name = "receive", description = "Receive a card from a channel")
public class ReceiveVCardCommand extends AbstractChannelCommand<VCard, VCard> {

    private long timeout;

    private boolean autoExpunge;

    public ReceiveVCardCommand(Consumer<VCard> consumer) {
        super(consumer);
    }

    @Override
    public Integer call() {
        boolean success = getEndpoint().receive(getConsumer(), timeout, autoExpunge);
        return success ? 0 : 1;
    }
}
