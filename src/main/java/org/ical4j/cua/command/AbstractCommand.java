package org.ical4j.cua.command;

import picocli.CommandLine;

import java.net.URL;

public abstract class AbstractCommand implements Runnable {

    @CommandLine.ArgGroup(multiplicity = "1")
    protected Input input;

    static class Input {
        @CommandLine.Option(names = {"-U", "--url"}, required = true)
        protected URL url;

        @CommandLine.Option(names = {"-F", "--file"}, required = true)
        protected String filename;

        @CommandLine.Option(names = {"--stdin"}, required = true)
        protected boolean stdin;
    }

}
