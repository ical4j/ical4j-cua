package org.ical4j.command;

import org.ical4j.command.collection.ListObjectUids;
import org.ical4j.command.store.*;
import picocli.CommandLine;

@CommandLine.Command(name = "collection", description = "Command group for collection operations",
        subcommands = {GetCollectionDetails.class, ListCollections.class,
        CreateCollection.class, UpdateCollection.class,
                DeleteCollection.class, ListObjectUids.class},
        mixinStandardHelpOptions = true)
public class CollectionCommandGroup {

}
