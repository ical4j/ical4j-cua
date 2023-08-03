package org.ical4j.command.collection;

import picocli.CommandLine;

@CommandLine.Command(name = "collection", description = "Command group for collection operations",
        subcommands = {GetCollectionDetails.class, ListCollections.class,
        CreateCollection.class, UpdateCollection.class,
                DeleteCollection.class, ListObjectUids.class},
        mixinStandardHelpOptions = true)
public class CollectionGroup {

}
