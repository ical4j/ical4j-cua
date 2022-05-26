package org.ical4j.cua;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.metatype.annotations.Designate;

import javax.servlet.Servlet;
import javax.servlet.http.HttpServlet;

@Component(
        service = {HttpServlet.class, Servlet.class},
        property = {"service.description=iCalendar User Agent Servlet"}
)
@Designate(ocd = ICalendarUserAgentServletConfiguration.class, factory = true)
public class ICalendarUserAgentServlet extends HttpServlet {
}
