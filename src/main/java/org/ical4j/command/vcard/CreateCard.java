package org.ical4j.command.vcard;

import net.fortuna.ical4j.data.ParserException;
import net.fortuna.ical4j.model.ConstraintViolationException;
import net.fortuna.ical4j.vcard.property.Uid;
import org.ical4j.connector.CardCollection;
import org.ical4j.connector.CardStore;
import org.ical4j.connector.ObjectNotFoundException;
import org.ical4j.connector.ObjectStoreException;
import picocli.CommandLine;

import java.io.IOException;
import java.util.function.Consumer;

import static org.ical4j.connector.ObjectCollection.DEFAULT_COLLECTION;

@CommandLine.Command(name = "create", description = "Create a new card")
public class CreateCard extends AbstractCardCommand<Uid> {

    public CreateCard(String collectionName, Consumer<Uid> consumer) {
        super(collectionName, consumer);
    }

    public CreateCard(CardStore<CardCollection> store) {
        super(DEFAULT_COLLECTION, store);
    }


    @Override
    public Integer call() {
        try {
            getConsumer().accept(getCollection().addCard(getCard()));
        } catch (ObjectStoreException | ConstraintViolationException | ObjectNotFoundException | ParserException |
                 IOException e) {
            throw new RuntimeException(e);
        }
        return 0;
    }
}
