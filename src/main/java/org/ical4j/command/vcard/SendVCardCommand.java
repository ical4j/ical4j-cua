package org.ical4j.command.vcard;

import net.fortuna.ical4j.vcard.VCard;
import org.ical4j.command.AbstractChannelCommand;
import picocli.CommandLine;

@CommandLine.Command(name = "send", description = "Send a card on a channel")
public class SendVCardCommand extends AbstractChannelCommand<VCard, Void> {

    private VCard card;

    public SendVCardCommand withCard(VCard card) {
        this.card = card;
        return this;
    }

    @Override
    public Integer call() {
        boolean success = getEndpoint().send(() -> card);
        return success ? 0 : 1;
    }
}
