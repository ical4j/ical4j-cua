package org.ical4j.cua;

import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;

@ObjectClassDefinition(name = "iCalendar Query Servlet", description = "Servlet Configuration for an iCalendar query service")
@interface ICalendarUserAgentServletConfiguration {

    @AttributeDefinition(name = "alias", description = "Servlet alias")
    String alias() default "/cua";
}
