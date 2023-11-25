package it.unibo.mvc;

import java.util.*;

public final class SimpleController implements Controller {

    private String lastString;
    private final List<String> history = new ArrayList<String>();

    @Override
    public void setNextString(final String nexString) {
        this.lastString = Objects.requireNonNull(nexString);
        this.history.add(nexString);
    }

    @Override
    public String getNextString() {
        return Objects.requireNonNull(this.lastString);
    }

    @Override
    public List<String> getHistoryOfPrintedStrings() {
        if(Objects.isNull(this.history)) {
            throw new IllegalStateException("Cannot print an empty history");
        }
        return Objects.requireNonNull(this.history);
    }

    @Override
    public void printCurrentString() {
        if(Objects.isNull(this.lastString)) {
            throw new IllegalStateException("Cannot print an unset string");
        }
        else {
            System.out.println(this.lastString);
        }
    }

}
