# iCal4j Command Line

A command framework for managing iCalendar and vCard data.

## Overview

iCal4j Command is a collection of operations for common iCalendar and vCard
use cases. This includes direct manipulation of data objects, integration with
persistence stores, and systems interoperability.

## Calendar Commands

The following commands directly manipulate iCalendar objects:

* Validator - check validity of iCalendar data
* Filter - select a subset of iCalendar components using filter expressions
* Free Busy - generate free/busy information for specified iCalendar data
* Replace UIDs - regenerate UIDs for all iCalendar components

## vCard Commands

* ...


## Template Commands

* Event - generate/modify iCalendar data using shorthand template input

## Store Commands

* Create Collection - create a new collection for a calendar store
* ...

## Collection Commands

* ...

## Channel Commands

* ...


## System Properties

The following table describes System properties applicable to the iCal4j User Agent.


| System Property                 | Description                                                                     | Value                                                | Default                                      |
|---------------------------------|---------------------------------------------------------------------------------|------------------------------------------------------|----------------------------------------------|
| org.ical4j.command.prodid       | An application identifier used as the value for the iCalendar `PRODID` property | A string (e.g. `-//Apple Inc.//Mac OS X 10.8.5//EN`) | `-//iCal4j//User Agent//EN`                  |
| org.ical4j.command.organizer    | The default URI used as the value for the iCalendar `ORGANIZER` property        | A URI (e.g. `mailto:johnd@example.com`)              | -                                            |
| org.ical4j.command.uidgenerator | The `UidGenerator` implementation used to create `UID` properties               | A fully qualified class name                         | `net.fortuna.ical4j.util.RandomUidGenerator` |
 

## Command Line Usage

### Publish a collection of events

    ical4j publish -F ./Australian32Holidays.ics

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
