package org.ical4j.cua.command;

import net.fortuna.ical4j.model.Calendar;
import picocli.CommandLine;

public class VersionProvider implements CommandLine.IVersionProvider {

    private final String queryVersion;

    private final String ical4jVersion;

    private final String javaVersion;

    public VersionProvider() {
        queryVersion = getClass().getPackage().getImplementationVersion();
        ical4jVersion = Calendar.class.getPackage().getImplementationVersion();
        javaVersion = System.getProperty("java.version");
    }

    @Override
    public String[] getVersion() {
        return new String[] {"iCal4j Query " + queryVersion,
                "\niCal4j: " + ical4jVersion, "\nJVM: " + javaVersion
        };
    }
}
