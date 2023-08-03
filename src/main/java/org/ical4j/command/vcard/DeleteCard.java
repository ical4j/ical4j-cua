package org.ical4j.command.vcard;

import net.fortuna.ical4j.vcard.VCard;
import org.ical4j.command.AbstractCollectionCommand;
import org.ical4j.connector.*;
import picocli.CommandLine;

import java.util.function.Consumer;

@CommandLine.Command(name = "delete", description = "Delete an existing card")
public class DeleteCard extends AbstractCollectionCommand<CardCollection, VCard> {

    @CommandLine.Option(names = {"-uid"})
    private String cardUid;

    public DeleteCard() {
        super();
    }

    public DeleteCard(String collectionName, Consumer<VCard> consumer) {
        super(collectionName, consumer);
    }

    public DeleteCard(String collectionName, Consumer<VCard> consumer, ObjectStore<CardCollection> store) {
        super(collectionName, consumer, store);
    }

    public DeleteCard withCardUid(String cardUid) {
        this.cardUid = cardUid;
        return this;
    }

    @Override
    public Integer call() {
        try {
            getConsumer().accept(getCollection().removeCard(cardUid));
        } catch (FailedOperationException | ObjectNotFoundException | ObjectStoreException e) {
            throw new RuntimeException(e);
        }
        return 0;
    }
}
