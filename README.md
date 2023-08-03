# iCal4j Command Line

A command framework for managing iCalendar and vCard data.

## System Properties

The following table describes System properties applicable to the iCal4j User Agent.


| System Property                   | Description                                                                     | Value                                                | Default                                      |
|-----------------------------------|---------------------------------------------------------------------------------|------------------------------------------------------|----------------------------------------------|
| org.ical4j.command.prodid       | An application identifier used as the value for the iCalendar `PRODID` property | A string (e.g. `-//Apple Inc.//Mac OS X 10.8.5//EN`) | `-//iCal4j//User Agent//EN`                  |
| org.ical4j.command.organizer    | The default URI used as the value for the iCalendar `ORGANIZER` property        | A URI (e.g. `mailto:johnd@example.com`)              | -                                            |
| org.ical4j.command.uidgenerator | The `UidGenerator` implementation used to create `UID` properties               | A fully qualified class name                         | `net.fortuna.ical4j.util.RandomUidGenerator` |
 

## Command Line Usage

### Publish a collection of events

    ical4j/bin/ical4j publish -F ./Australian32Holidays.ics

Result:

```
BEGIN:VCALENDAR
PRODID:iCal4j User Agent
VERSION:2.0
METHOD:PUBLISH
BEGIN:VEVENT
UID:D416469E-C414-11D6-BA97-003065F198AC
DTSTAMP:20020906T094459Z
SUMMARY:Australia Day
RRULE:FREQ=YEARLY;INTERVAL=1;BYMONTH=1
DTSTART;VALUE=DATE:20020126
DTEND;VALUE=DATE:20020127
ORGANIZER:johnd@example.com
SEQUENCE:0
END:VEVENT
...
```
