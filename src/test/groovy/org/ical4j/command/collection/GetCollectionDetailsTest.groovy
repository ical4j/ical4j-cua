package org.ical4j.command.collection

import org.ical4j.command.store.GetCollectionDetails
import org.ical4j.connector.ObjectStore
import spock.lang.Specification

class GetCollectionDetailsTest extends Specification {

    def 'test get collection'() {
        given: 'a mock card store'
        ObjectStore store = Mock()

        when: 'a get collection command is run'
        new GetCollectionDetails((collection) -> {}, store).withCollectionName('testCollection').call()

        then: 'store get collection is invoked'
        1 * store.getCollection('testCollection')
    }
}
