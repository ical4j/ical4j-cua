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
}