module ical4j.command {
    requires java.base;
    requires ical4j.core;
    requires ical4j.vcard;
    requires ical4j.connector.api;

    requires info.picocli;
    requires ical4j.integration.api;
    requires com.fasterxml.jackson.databind;
    requires com.fasterxml.jackson.dataformat.xml;
    requires ical4j.serializer;
    requires org.slf4j;

    exports org.ical4j.command;
    exports org.ical4j.command.calendar;
    exports org.ical4j.command.channel;
    exports org.ical4j.command.collection;
    exports org.ical4j.command.store;
    exports org.ical4j.command.template;
    exports org.ical4j.command.vcard;

    opens org.ical4j.command to info.picocli;
    opens org.ical4j.command.calendar to info.picocli;
    opens org.ical4j.command.channel to info.picocli;
    opens org.ical4j.command.collection to info.picocli;
    opens org.ical4j.command.store to info.picocli;
    opens org.ical4j.command.template to info.picocli;
    opens org.ical4j.command.vcard to info.picocli;
}