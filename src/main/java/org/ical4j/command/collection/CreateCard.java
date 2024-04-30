package org.ical4j.command.collection;

import net.fortuna.ical4j.data.ParserException;
import net.fortuna.ical4j.model.ConstraintViolationException;
import net.fortuna.ical4j.vcard.VCard;
import org.ical4j.command.InputOptions;
import org.ical4j.command.calendar.FilterCalendar;
import org.ical4j.connector.CardCollection;
import org.ical4j.connector.CardStore;
import org.ical4j.connector.ObjectNotFoundException;
import org.ical4j.connector.ObjectStoreException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import picocli.CommandLine;

import java.io.IOException;
import java.util.function.Consumer;

import static org.ical4j.connector.ObjectCollection.DEFAULT_COLLECTION;

@CommandLine.Command(name = "create-card", description = "Create a new card")
public class CreateCard extends AbstractCollectionCommand<CardCollection, String> {

    private static final Logger LOGGER = LoggerFactory.getLogger(CreateCard.class);

    @CommandLine.ArgGroup(multiplicity = "1")
    protected InputOptions input;

    private VCard card;

    public CreateCard(String collectionName, Consumer<String> consumer) {
        super(collectionName, consumer);
    }

    public CreateCard(CardStore<CardCollection> store) {
        super(DEFAULT_COLLECTION, store);
    }

    public CreateCard withCard(VCard card) {
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
            getConsumer().accept(getCollection().add(getCard()));
        } catch (ObjectStoreException | ConstraintViolationException | ObjectNotFoundException | ParserException |
                 IOException e) {
            LOGGER.error("Unexpected error", e);
            return 1;
        }
        return 0;
    }
}
