package it.unibo.mvc;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

/**
 * Application controller. Performs the I/O.
 */
public class Controller {

    private final String HOME_DIR = System.getProperty("home.user");
    private final String DEFAULT_FILE = System.getProperty("output.txt");

    private File destination = new File(HOME_DIR + File.separator + DEFAULT_FILE);

    public File getCurrentDestination() {
        return this.destination;
    }

    public String getDestinationPath() {
        return this.destination.getPath();
    }

    public void setNewDestination(final File newDestination) {
        final File file = newDestination.getParentFile();
        if(file != null) {
            this.destination = file;
        }
        else {
            throw new IllegalArgumentException("Error: could not save to non-existing folder");
        }
    }

    public void save(final String string) throws IOException {
        try (PrintStream os = new PrintStream(this.destination, StandardCharsets.UTF_8)) {
                    os.println(string);
        }
    }

}
