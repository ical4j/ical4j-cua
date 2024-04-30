package org.ical4j.command.collection;

import net.fortuna.ical4j.data.ParserException;
import net.fortuna.ical4j.model.ConstraintViolationException;
import net.fortuna.ical4j.vcard.VCard;
import net.fortuna.ical4j.vcard.property.Uid;
import org.ical4j.command.InputOptions;
import org.ical4j.command.collection.AbstractCollectionCommand;
import org.ical4j.connector.*;
import picocli.CommandLine;

import java.io.IOException;
import java.util.function.Consumer;

@CommandLine.Command(name = "update-card", description = "Update an existing card")
public class UpdateCard extends AbstractCollectionCommand<CardCollection, Uid[]> {

    @CommandLine.ArgGroup(multiplicity = "1")
    protected InputOptions input;

    private VCard card;

    public UpdateCard() {
        super();
    }

    public UpdateCard(String collectionName, Consumer<Uid[]> consumer) {
        super(collectionName, consumer);
    }

    public UpdateCard(String collectionName, CardStore<CardCollection> store) {
        super(collectionName, store);
    }

    public UpdateCard withCard(VCard card) {
        this.card = card;
        return this;
    }

    public VCard getCard() throws ParserException, IOException {
        if (card == null) {
            card = input.toVCard();
        }
        return card;
    }

    @Override
    public Integer call() {
        try {
            getConsumer().accept(getCollection().merge(getCard()));
        } catch (ObjectStoreException | ConstraintViolationException | ObjectNotFoundException | IOException |
                 ParserException e) {
            throw new RuntimeException(e);
        }
        return 0;
    }
}
